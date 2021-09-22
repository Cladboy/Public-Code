import time

import pandas as pd
import numpy as np
import math
from sklearn import model_selection
from sklearn import metrics
from sklearn import linear_model
from sklearn import svm
import matplotlib.pyplot as plt
import re


def task_1a():
    movieReviewsDf = pd.read_excel("movie_reviews.xlsx")

    # Map sentiment values to 0 and 1 for negative and positive respectively
    movieReviewsDf['Sentiment'] = movieReviewsDf['Sentiment'].map({'negative': 0, 'positive': 1})

    # Extract Training Data
    movieTrainingData = movieReviewsDf[movieReviewsDf['Split'] == "train"][['Review']]
    movieTrainingLabels = movieReviewsDf[movieReviewsDf['Split'] == "train"][['Sentiment']]

    # Extract Test Data
    movieTestData = movieReviewsDf[movieReviewsDf['Split'] == "test"][['Review']]
    movieTestLabels = movieReviewsDf[movieReviewsDf['Split'] == "test"][['Sentiment']]

    # Number of Positive and Negative reviews for training data
    print("Training set - Positive Reviews: ", len(movieTrainingLabels[movieTrainingLabels["Sentiment"] == 1]))
    print("Training set - Negative Reviews: ", len(movieTrainingLabels[movieTrainingLabels["Sentiment"] == 0]))

    # Number of Positive and Negative reviews for test data
    print("Evaluation set - Positive Reviews: ", len(movieTestLabels[movieTestLabels["Sentiment"] == 1]))
    print("Evaluation set - Negative Reviews: ", len(movieTestLabels[movieTestLabels["Sentiment"] == 0]))
    print("-" * 75)

    return movieTrainingData, movieTrainingLabels, movieTestData, movieTestLabels


# Removes non-alphanumeric characters and creates of lists of lowercase words
def task_1b_1(data, isDict=True):
    if isDict:
        toReturn = []
        for i in data["Review"]:
            temp = re.sub("[^a-zA-Z0-9]+", " ", i)
            temp = temp.lower()
            temp = temp.split()
            toReturn.append(temp)
        return toReturn

    if not isDict:
        temp = re.sub("[^a-zA-Z0-9]+", " ", data)
        temp = temp.lower()
        temp = temp.split()
        return temp


# Counts number of occurrences of each word and outputs words that fit within minimum word length and occurrences
def task_1b_2(data, minLength, minCount):
    # Valid words
    words = []

    # Key, Value : Word(String), occurrences(Int)
    wordsDict = {}

    # Counts total number of words and puts into word dictionary
    for review in data:
        for word in review:
            if len(word) >= minLength:
                if word in wordsDict:
                    wordsDict[word] += 1
                else:
                    wordsDict[word] = 1

    # Adds all words with minimum word occurrence
    for key, value in wordsDict.items():
        if value >= minCount:
            words.append(key)

    return words


def task_1b(data, minLength, minCount):
    formattedReviews = task_1b_1(data)
    # print(formattedReviews)
    words = task_1b_2(formattedReviews, minLength, minCount)
    return words


def task_1c_1(data, words):
    wordsDict = words
    # print(words)

    # Counts total number of words and puts into word dictionary
    for review in data:
        for word in review:
            if word in wordsDict:
                wordsDict[word] += 1

    return wordsDict


def task_1c(data, labels, minLength, minCount):
    mappedPositiveWords = {}
    mappedNegativeWords = {}
    positiveReviews = []
    negativeReviews = []

    # Get sanitised reviews
    formattedReviews = task_1b_1(data)
    # print("len(formattedReviews) = ", len(formattedReviews))
    # print("len(labels) = ", len(labels))

    # Get valid words from whole set of reviews
    reviewWords = task_1b(data, minLength, minCount)
    mappedWords = {}
    for word in reviewWords:
        mappedWords[word] = 0

    # print(formattedReviews)
    # print(labels)

    reviewIndexTick = 0
    for key, value in labels["Sentiment"].items():
        # print("Key: ", key, "\tValue: ", value)
        if value == 1:
            positiveReviews.append(formattedReviews[reviewIndexTick])

        elif value == 0:
            negativeReviews.append(formattedReviews[reviewIndexTick])

        reviewIndexTick += 1

    # print(positiveReviews)
    # print("len(positiveReviews): ", len(positiveReviews))
    # print("len(negativeReviews): ", len(negativeReviews))
    mappedPositiveWords = mappedWords.copy()
    mappedNegativeWords = mappedWords.copy()
    mappedPositiveWords = task_1c_1(positiveReviews, mappedPositiveWords)
    mappedNegativeWords = task_1c_1(negativeReviews, mappedNegativeWords)

    return mappedPositiveWords, mappedNegativeWords


def task_1d(data, labels, minLength, minCount):
    alpha = 1

    totalReviews = len(data)
    totalPositiveReviews = len(labels[labels["Sentiment"] == 1])
    totalNegativeReviews = len(labels[labels["Sentiment"] == 0])
    # print(totalReviews)
    # print(totalPositiveReviews)
    # print(totalNegativeReviews)

    reviewWords = task_1b(data, minLength, minCount)
    mappedWords = {}
    for word in reviewWords:
        mappedWords[word] = 0

    # Dictionary to record likelihood
    likelihoodPositiveReview = mappedWords.copy()
    likelihoodNegativeReview = mappedWords.copy()

    mappedPositiveWords, mappedNegativeWords = task_1c(data, labels, 10, 10)

    for word in mappedPositiveWords:
        likelihoodPositiveReview[word] = (mappedPositiveWords[word] + alpha) / (totalPositiveReviews + 2 * alpha)

    for word in mappedNegativeWords:
        likelihoodNegativeReview[word] = (mappedNegativeWords[word] + alpha) / (totalNegativeReviews + 2 * alpha)

    # Priors
    priorPositiveReview = totalPositiveReviews / totalReviews
    priorNegativeReview = totalNegativeReviews / totalReviews

    return likelihoodPositiveReview, likelihoodNegativeReview, priorPositiveReview, priorNegativeReview


def task_1e(data, likelihoodPositiveReview, likelihoodNegativeReview, priorPositiveReview, priorNegativeReview):
    prediction = []
    for review in data["Review"]:
        # print(review)
        cleanWords = task_1b_1(review, False)
        logChanceOfPositive = 0
        logChanceOfNegative = 0

        for word in cleanWords:
            # print(word)
            if word in likelihoodPositiveReview:
                logChanceOfPositive = logChanceOfPositive + math.log(likelihoodPositiveReview[word])
                logChanceOfNegative = logChanceOfNegative + math.log(likelihoodNegativeReview[word])

        if math.log(priorPositiveReview) - math.log(priorNegativeReview) < logChanceOfPositive - logChanceOfNegative:
            prediction.append(1)
        else:
            prediction.append(0)

    return prediction


def task_1f():
    kf = model_selection.KFold(n_splits=6, shuffle=True)
    minWordLength = 10
    accuracyScores = []
    movieTrainingData, movieTrainingLabels, movieTestData, movieTestLabels = task_1a()

    for i in range(1, 11):
        print("\n", "-" * 75, "\nCurrent minimum word Length: ", i)
        results = []
        for review in kf.split(movieTrainingData):
            likelihoodPositiveReview, likelihoodNegativeReview, priorPositiveReview, priorNegativeReview \
                = task_1d(movieTrainingData, movieTrainingLabels, i, minWordLength)

            tempResults = task_1e(movieTrainingData, likelihoodPositiveReview,
                                  likelihoodNegativeReview, priorPositiveReview, priorNegativeReview)

            results.append(metrics.accuracy_score(tempResults, movieTrainingLabels))
        accuracyScores.append(np.mean(results))

    print("KFold accuracy scores: ", accuracyScores)


def task_1():
    '''
    # Task 1a
    movieTrainingData, movieTrainingLabels, movieTestData, movieTestLabels = task_1a()

    # Task 1b
    # task_1b(movieTrainingData, 10, 10)

    # Task 1c
    mappedPositiveWords, mappedNegativeWords = task_1c(movieTrainingData, movieTrainingLabels, 10, 10)

    # Task 1d
    likelihoodPositiveReview, likelihoodNegativeReview, priorPositiveReview, priorNegativeReview = task_1d(movieTrainingData, movieTrainingLabels, 10, 10)
    # print(likelihoodPositiveReview)
    # print(likelihoodNegativeReview)
    # print(priorPositiveReview)
    # print(priorNegativeReview)

    # Task 1e
    task1e = task_1e(movieTrainingData, likelihoodPositiveReview, likelihoodNegativeReview, priorPositiveReview, priorNegativeReview)
    print(task1e)
    '''
    task_1f()

    # print("Positive words: ", mappedPositiveWords)
    # print("Negative words: ", mappedNegativeWords)
    return


# ----------------------------------------------------- TASK 2 -----------------------------------------------------


def task_2a(sampleNumber=0.07):
    productDf = pd.read_csv("product_images.csv")
    # print(productDf.head())

    productFeatureVectors = productDf.loc[:, productDf.columns != 'label'].values
    productLabels = productDf['label'].values

    # Print the number of sneakers and ankle boots
    print("Sneakers   : ", len(productLabels[productLabels == 0]))
    print("Ankle boots: ", len(productLabels[productLabels == 1]))

    # prints example of Sneaker and Ankle Boot respectively
    plt.figure(1, figsize=(3, 3))
    plt.imshow(np.reshape(productFeatureVectors[3], (28, 28)), cmap='gray', interpolation='nearest')
    plt.show()

    plt.figure(1, figsize=(3, 3))
    plt.imshow(np.reshape(productFeatureVectors[0], (28, 28)), cmap='gray', interpolation='nearest')
    plt.show()



    productFeatureVectorsSampleSize = int(float(str(sampleNumber * len(productFeatureVectors))))
    productLabelsSampleSize = int(float(str(sampleNumber * len(productLabels))))
    print(productFeatureVectorsSampleSize, " <- ", sampleNumber * len(productFeatureVectors))
    print(productLabelsSampleSize, " < - ", sampleNumber * len(productLabels))



    productTrainingData = productFeatureVectors[0:productFeatureVectorsSampleSize]
    productTrainingTarget = productLabels[0:productLabelsSampleSize]
    productTestData = productFeatureVectors[productFeatureVectorsSampleSize:len(productFeatureVectors)]
    productTestTarget = productLabels[productLabelsSampleSize:len(productLabels)]

    return productTrainingData, productTrainingTarget, \
           productTestData, productTestTarget, sampleNumber * len(productFeatureVectors)

def task_2b(productTrainingData, productingTrainingTarget, productTestData, productTestTarget, productNoSamples):
    print("\n" * 10, "-" * 20, "Task 2b", "-" * 20)
    kFold = model_selection.KFold(n_splits=6, shuffle=True)

    trainingTimes = []
    predictionTimes = []
    highestScore = 0
    for trainCount, testCount in kFold.split(productTrainingData):

        perceptron = linear_model.Perceptron()

        startTestTime = time.time()
        perceptron.fit(productTrainingData[trainCount], productingTrainingTarget[trainCount])
        stopTestTime = time.time() - startTestTime
        trainingTimes.append(stopTestTime)
        print("\n----Split----\nTest Training Time: ", stopTestTime, " seconds\n")

        startTestTime = time.time()
        prediction = perceptron.predict(productTrainingData[testCount])
        stopTestTime = time.time() - startTestTime
        predictionTimes.append(stopTestTime)
        print("----Split----\nTest Prediction Time: ", stopTestTime, " seconds")

        # print("productingTrainingTarget[testCount]: ", productingTrainingTarget[testCount])
        # print("prediction: ", prediction)
        score = metrics.accuracy_score(productingTrainingTarget[testCount], prediction)
        confusionMatrix = metrics.confusion_matrix(productingTrainingTarget[testCount], prediction)


        print("Accuracy Score: ", score)
        print("True sneakers:", np.sum(confusionMatrix[0, 0]))
        print("True ankle boots:", np.sum(confusionMatrix[1, 1]))
        print("False sneakers:", np.sum(confusionMatrix[1, 0]))
        print("False ankle boots:", np.sum(confusionMatrix[0, 1]))

        if highestScore < score:
            highestPerceptron = perceptron

        testPrediction = highestPerceptron.predict(productTestData)
        print("Prediction Accuracy: ", metrics.accuracy_score(productTestTarget, testPrediction))

        print("Minimum Training Time per training sample:", min(trainingTimes) / productNoSamples, " seconds")
        print("Maximum Training Time per training sample:", max(trainingTimes) / productNoSamples, " seconds")
        print("Average Training Time per training sample:",
              (sum(trainingTimes) / len(trainingTimes)) / productNoSamples, " seconds")

        print("Minimum Prediction Time per evaulation sample:", min(predictionTimes) / productNoSamples, " seconds")
        print("Maximum Prediction Time per evaulation sample:", max(predictionTimes) / productNoSamples, " seconds")
        print("Average Prediction Time per evaulation sample:",
              (sum(predictionTimes) / len(predictionTimes)) / productNoSamples, " seconds")

def task_2c_1(productTrainingData, productTrainingTarget, productTestData, productTestTarget,
              productNoSamples, type="linear"):

    print("\n" * 5, "-" * 20, "Task 2b, type: ", type, "-" * 20)
    kFold = model_selection.KFold(n_splits=6, shuffle=True)

    # Task b exists here too but also kept in function task_2b to fit with assignment layout
    if type == "linear":
        functionKernal = svm.SVC(kernel="linear")
    elif type == "rbf":
        functionKernal = svm.SVC(kernel="rbf", gamma=1e-10)
    elif type == "perceptron":
        functionKernal = linear_model.Perceptron()
    else:
        print("Invalid type")
        return

    trainingTimes = []
    predictionTimes = []
    highestScore = 0
    for trainCount, testCount in kFold.split(productTrainingData):

        startTestTime = time.time()
        functionKernal.fit(productTrainingData[trainCount], productTrainingTarget[trainCount])
        stopTestTime = time.time() - startTestTime
        trainingTimes.append(stopTestTime)
        print("----Split----\nTest Training Time: ", stopTestTime, " seconds")

        startTestTime = time.time()
        prediction = functionKernal.predict(productTrainingData[testCount])
        stopTestTime = time.time() - startTestTime
        predictionTimes.append(stopTestTime)
        print("----Split----\nTest Prediction Time: ", stopTestTime, " seconds")

        # print("productingTrainingTarget[testCount]: ", productingTrainingTarget[testCount])
        # print("prediction: ", prediction)
        score = metrics.accuracy_score(productTrainingTarget[testCount], prediction)
        confusionMatrix = metrics.confusion_matrix(productTrainingTarget[testCount], prediction)

        print(type, " Accuracy Score: ", score)
        print(type, " True sneakers:", np.sum(confusionMatrix[0, 0]))
        print(type, " True ankle boots:", np.sum(confusionMatrix[1, 1]))
        print(type, " False sneakers:", np.sum(confusionMatrix[1, 0]))
        print(type, " False ankle boots:", np.sum(confusionMatrix[0, 1]))

        if highestScore < score:
            highestFunction = functionKernal

        testPrediction = highestFunction.predict(productTestData)
        print(type, " Prediction Accuracy: ", metrics.accuracy_score(productTestTarget, testPrediction))

        print(type, " Minimum Training Time per training sample:", min(trainingTimes) / productNoSamples, " seconds")
        print(type, " Maximum Training Time per training sample:", max(trainingTimes) / productNoSamples, " seconds")
        print(type, " Average Training Time per training sample:",
              (sum(trainingTimes) / len(trainingTimes)) / productNoSamples, " seconds")

        print(type, " Minimum Prediction Time per evaulation sample:", min(predictionTimes) / productNoSamples, " seconds")
        print(type, " Maximum Prediction Time per evaulation sample:", max(predictionTimes) / productNoSamples, " seconds")
        print(type, " Average Prediction Time per evaulation sample:",
              (sum(predictionTimes) / len(predictionTimes)) / productNoSamples, " seconds")


def task_2c(productTrainingData, productTrainingTarget, productTestData, productTestTarget, productNoSamples):
    print("\n"*10, "-"*20, "Task 2c", "-"*20)
    task_2c_1(productTrainingData, productTrainingTarget, productTestData, productTestTarget, productNoSamples, "linear")
    task_2c_1(productTrainingData, productTrainingTarget, productTestData, productTestTarget, productNoSamples, "rbf")


def task_2():
    # productTrainingData, productTrainingTarget, productTestData, productTestTarget, productNoSamples = task_2a(0.05)
    productTrainingData, productTrainingTarget, productTestData, productTestTarget, productNoSamples = task_2a()
    task_2b(productTrainingData, productTrainingTarget, productTestData, productTestTarget, productNoSamples)
    task_2c(productTrainingData, productTrainingTarget, productTestData, productTestTarget, productNoSamples)
    return


# ----------------------------------------------------- TASK 3 -----------------------------------------------------


# Preprocessing
def task_3ab(data):
    # Task 3a
    print(data.head())
    Quality = data[["cut", "color", "clarity"]]
    Cut = Quality[["cut"]]
    Color = Quality[["color"]]
    Clarity = Quality[["clarity"]]

    Cut_Unique = Quality["cut"].unique()
    Color_Unique = Quality["color"].unique()
    Clarity_Unique = Quality["clarity"].unique()

    Target_Dict = {}
    Feature_Dict = {}

    # Task 3b
    for Cut in Cut_Unique:
        for Color in Color_Unique:
            for Clarity in Clarity_Unique:
                Key = Cut + "," + Color + "," + Clarity
                amount = data[data["cut"] == Cut][data["color"] == Color][data["clarity"] == Clarity]
                value = amount[["carat", "table", "depth"]].values
                Price = amount[["price"]].values
                if len(amount) >= 800:
                    Feature_Dict.update({Key: value})
                    Target_Dict.update({Key: Price})

    print(Feature_Dict)
    print(Target_Dict)

    return Feature_Dict, Target_Dict


# Task c1
def calculate_model_function(deg, data, p):
    r = 0
    t = 0
    for n in range(deg + 1):
        for i in range(n + 1):
            for j in range(n + 1):
                for k in range(n + 1):
                    if i + j + k == n:
                        r += p[t] * (data[:, 0] ** i) * (data[:, 1] ** j * (data[:, 2] ** k))
                        t = t + 1
    return r


# Task d
def num_coefficients_3(d):
    t = 0
    for n in range(d + 1):
        for i in range(n + 1):
            for j in range(n + 1):
                for k in range(n + 1):
                    if i + j + k == n:
                        t = t + 1
    return t


# Task 3 Linearize
def linearize(deg, data, p0):
    f0 = calculate_model_function(deg, data, p0)
    J = np.zeros((len(f0), len(p0)))
    epsilon = 1e-6
    for i in range(len(p0)):
        p0[i] += epsilon
        fi = calculate_model_function(deg, data, p0)
        p0[i] -= epsilon
        di = (fi - f0) / epsilon
        J[:, i] = di
    return f0, J


# Task 4 Calculate update
def calculate_update(y, f0, J):
    l = 1e-2
    N = np.matmul(J.T, J) + l * np.eye(J.shape[1])
    r = y - f0
    n = np.matmul(J.T, r)
    dp = np.linalg.solve(N, n)
    return dp


# Task 5 Regression
def regression(deg, data, target):
    max_iter = 10
    p0 = np.zeros(num_coefficients_3(deg))
    for i in range(max_iter):
        f0, J = linearize(deg, data, p0)
        dp = calculate_update(target, f0, J)
        p0 += dp
    return p0


# Main of Task 3
def task_3():
    data = pd.read_csv("diamonds.csv")
    Features, Targets = task_3ab(data)
    Degree = 3
    for i in Features:
        print("i == " + i)
        for j in Targets:
            print(Features[i])
            print(Targets[j])
            p0 = regression(Degree, Features[i], Targets[j])
            break
        break


# ----------------------------------------------------- MAIN -----------------------------------------------------


def main():
    task_1()
    # task_2()
    # task_3()
main()
