# --------------------------------------------------------
#
# PYTHON PROGRAM DEFINITION
#
# The knowledge a computer has of Python can be specified in 3 levels:
# (1) Prelude knowledge --> The computer has it by default.
# (2) Borrowed knowledge --> The computer gets this knowledge from 3rd party libraries defined by others
#                            (but imported by us in this program).
# (3) Generated knowledge --> The computer gets this knowledge from the new functions defined by us in this program.
#
# When launching in a terminal the command:
# user:~$ python3 this_file.py
# our computer first processes this PYTHON PROGRAM DEFINITION section of the file.
# On it, our computer enhances its Python knowledge from levels (2) and (3) with the imports and new functions
# defined in the program. However, it still does not execute anything.
#
# --------------------------------------------------------

# ------------------------------------------
# IMPORTS
# ------------------------------------------
import pyspark
import pyspark.sql.functions

# ------------------------------------------
# FUNCTION my_main
# ------------------------------------------
def my_main(spark, my_dataset_dir, source_node):
    # 1. We define the Schema of our DF.
    my_schema = pyspark.sql.types.StructType(
        [pyspark.sql.types.StructField("source", pyspark.sql.types.IntegerType(), False),
         pyspark.sql.types.StructField("target", pyspark.sql.types.IntegerType(), False),
         pyspark.sql.types.StructField("weight", pyspark.sql.types.IntegerType(), False)
         ])

    # 2. Operation C1: 'read' to create the DataFrame from the dataset and the schema
    inputDF = spark.read.format("csv") \
        .option("delimiter", " ") \
        .option("quote", "") \
        .option("header", "false") \
        .schema(my_schema) \
        .load(my_dataset_dir)

    # ------------------------------------------------
    # START OF YOUR CODE:
    # ------------------------------------------------

    # Remember that the entire work must be done “within Spark”:
    # (1) The function my_main must start with the creation operation 'read' above loading the dataset to Spark SQL.
    # (2) The function my_main must finish with an action operation 'collect', gathering and printing by the screen the result of the Spark SQL job.
    # (3) The function my_main must not contain any other action operation 'collect' other than the one appearing at the very end of the function.
    # (4) The resVAL iterator returned by 'collect' must be printed straight away, you cannot edit it to alter its format for printing.

    # Type all your code here. Use as many Spark SQL operations as needed.

    #Importing specific functions to make 'pyspark.sql.function.<function>' calls more readable
    from pyspark.sql.functions import lit
    from pyspark.sql.functions import when
    from pyspark.sql.functions import col

    #inputDF.show()

    #Create priority queue and unvisitedVertices
    edge_candidates = inputDF[inputDF.source.isin(source_node)]
    #edge_candidates.show()
    unvistedVertices = inputDF.select("source").distinct()

    priorityQueue = unvistedVertices.withColumn("totalCost", lit(-1)).withColumn("from", lit(""))
    priorityQueue = priorityQueue.withColumn("totalCost", when(priorityQueue.source == source_node, 0).otherwise(col("totalCost")))
    priorityQueue = priorityQueue.withColumn("from", when(priorityQueue.source == source_node, str(source_node)).otherwise(col("from")))
    #priorityQueue.show()

    while (edge_candidates.count() > 0):
        #print("Yo")
        #print(edge_candidates)
        #edge_candidates.show()
        
        
        # 4.1. We pick the best edge candidate
        #----------------------------------------------------------------------------------------------------
        #res = ()

        # 1.1. We output the best source_node
        selected_candidate = edge_candidates.take(1)#edge_candidates[0]
        best_source_node = selected_candidate[0][0]#selected_candidate[0]
        #print(selected_candidate)
        #print(best_source_node)
        

        # 1.2. We output the best target_node
        best_target_node = selected_candidate[0][1]#selected_candidate[1]
        #print(best_target_node)
        
        # 1.3. We output the best cost
        #best_cost = shortest_path_per_node[best_source_node][0] + selected_candidate[2]
        #priorityQueue.show()
        best_cost = priorityQueue[priorityQueue.source.isin(best_source_node)].take(1)
        #best_cost = best_cost.take(1)
        best_cost = int(best_cost[0][1]) + int(selected_candidate[0][2])
        #print(selected_candidate[0][2])
        #best_cost += int(selected_candidate[0][2])
        #print(best_cost)
        
        # 2. We traverse the rest of edge_candidates to get the one with best cost
        for selected_candidate in (edge_candidates.collect())[1:]:
            #print("Selected Candidate:")
            #print(selected_candidate)
            #print(selected_candidate[0])
            #pass
            # 2.1. We compute the new info associated to the candidate
            new_source_node = selected_candidate[0]
            new_target_node = selected_candidate[1]
            new_cost = priorityQueue[priorityQueue.source.isin(new_source_node)].take(1)
            #print(new_cost[0][1])
            new_cost = int(new_cost[0][1]) + int(selected_candidate[2])#shortest_path_per_node[new_source_node][0] + selected_candidate[2]
            

            # 2.2. If new_cost improves best_cost, we update it
            if (new_cost < best_cost):
                best_source_node = new_source_node
                best_target_node = new_target_node
                best_cost = new_cost
                #print(best_cost)
        
        
        # 3. We assign res
        #res = (best_source_node, best_target_node, best_cost)
        #print(res)
        
        #-------------------------------------------------------------------------------------------
        #(best_source_node, best_target_node, best_cost) = res



        # 4.2. We update the shortest_path for best_target_node

        # 4.2.1. We compute such path as the one to best_source_node plus the edge best_source_node --> best_target_node
        #best_path = res[best_source_node][1] + "-" + str(best_target_node)
        best_path = priorityQueue[priorityQueue.source.isin(best_source_node)].take(1)[0][2] + "-" + str(best_target_node)
        #best_path += + "-" + str(best_target_node)
        #print(best_path)
        
        # 4.2.2. We set the info for the node
        #res[best_target_node] = (best_cost, best_path)
        priorityQueue = priorityQueue.withColumn("totalCost", when(priorityQueue.source == best_target_node, best_cost).otherwise(col("totalCost"))).withColumn("from", when(priorityQueue.source == best_target_node, best_path).otherwise(col("from")))
        #priorityQueue.show()
        
        # 4.3. We remove all edge_candidates pointing to best_target_node
        #edge_candidates.show()
        #edge_candidates = list( filter(lambda item : item[1] != best_target_node, edge_candidates) )
        #edge_candidates.show()
        edge_candidates = edge_candidates[~edge_candidates.target.isin(best_target_node)]
        #print(edge_candidates)
        #edge_candidates.show()
        

        # 4.4. We add all new edges to become candidates

        # 4.4.1. We get the edges from 'best_target_node', to see all the edges departing from it
        potential_new_candidates = inputDF[inputDF.source.isin(best_target_node)] #edges_per_node[best_target_node]
        #potential_new_candidates.show()
        

        # 4.4.2. We filter just these edges bringing us to nodes for which we have not found a shortest path yet.
        #new_candidates = list( filter(lambda item : res[item[1]][0] == -1, potential_new_candidates) )
        #new_candidates = edge_candidates[priorityQueue.totalCost.isin(-1)]
        unvisted = priorityQueue.filter(priorityQueue.totalCost == -1).select("source").collect()
        #unvisted.show()
        unvisted = [i[0] for i in unvisted]
        #print(unvisted)
        #best_cost = priorityQueue[priorityQueue.source.isin(best_source_node)]
        new_candidates = potential_new_candidates[potential_new_candidates.target.isin(unvisted)]
        #print("yooooooooooooo")
        #new_candidates.show()
        
        # 4.4.3 We append such new edges in the desired format
        edge_candidates = edge_candidates.union(new_candidates)
        #edge_candidates.show()


    #unvistedVertices.show()
    
    priorityQueue = priorityQueue.orderBy('source')
    solutionDF = priorityQueue.withColumnRenamed("source","id").withColumnRenamed("totalCost","cost").withColumnRenamed("from","path")
    #solutionDF = tempSolutionDF.withColumn

    #priorityQueue.show()





    # ------------------------------------------------
    # END OF YOUR CODE
    # ------------------------------------------------

    # Operation A1: 'collect' to get all results
    resVAL = solutionDF.collect()
    for item in resVAL:
        print(item)

# --------------------------------------------------------
#
# PYTHON PROGRAM EXECUTION
#
# Once our computer has finished processing the PYTHON PROGRAM DEFINITION section its knowledge is set.
# Now its time to apply this knowledge.
#
# When launching in a terminal the command:
# user:~$ python3 this_file.py
# our computer finally processes this PYTHON PROGRAM EXECUTION section, which:
# (i) Specifies the function F to be executed.
# (ii) Define any input parameter such this function F has to be called with.
#
# --------------------------------------------------------
if __name__ == '__main__':
    # 1. We use as many input arguments as needed
    source_node = 1

    # 2. Local or Databricks
    local_False_databricks_True = False

    # 3. We set the path to my_dataset and my_result
    my_local_path = "../../../../3_Code_Examples/L15-25_Spark_Environment/"
    my_databricks_path = "/"

    my_dataset_dir = "FileStore/tables/6_Assignments/my_dataset_3/"

    if local_False_databricks_True == False:
        my_dataset_dir = my_local_path + my_dataset_dir
    else:
        my_dataset_dir = my_databricks_path + my_dataset_dir

    # 4. We configure the Spark Session
    spark = pyspark.sql.SparkSession.builder.getOrCreate()
    spark.sparkContext.setLogLevel('WARN')
    print("\n\n\n")

    # 5. We call to our main function
    my_main(spark, my_dataset_dir, source_node)
