#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>

void display(int size, char discovered[size][size], int cluesLeft){
    char yAxis = 'A';
    printf("\nClues Left: %d\n\t",cluesLeft);
    for (int i = 0; i < size; ++i) {
        printf("%c ",yAxis+i);
    }
    printf("\n");
    for (int y = 0; y < size; ++y) {
        printf("\n%c:  ",yAxis);
        yAxis++;
        for (int x = 0; x < size; ++x) {
            printf("%c ",discovered[y][x]);
        }
    }
    printf("\n-----------");
}

//Exists only for testing
void displayTest(int size, int clues[size][size]){
    for (int y = 0; y < size; ++y) {
        printf("\n");
        for (int x = 0; x < size; ++x) {
            printf("%d ",clues[y][x]);
        }
    }
}

char get_user_char(){
    char res = getchar();
    bool finish = false;
    char dummy_char = ' ';

    while(finish == false){
        dummy_char = getchar();
        if(dummy_char == '\n'){
            finish = true;
        }
    }
    return res;
}

bool guess(int size, int clues[size][size], char discovered[size][size], int y, int x){
    if(clues[y][x] == -1){
        discovered[y][x] = 'B';
        return false;
    }
    /*else{
        discovered[y][x] += 6 + clues[y][x];
        return true;
    }*/
    else{
        discovered[y][x] = 48 + clues[y][x];
        return true;
    }
}
void generateClues(int size, int clues[size][size], char discovered[size][size], int bombs){

    /*int test0 = -1;
    int test1 = 1;
    int test2 = test1 + test0;
    printf("%d",test2);*/
    srand(time(0));

    for (int y = 0; y < size; ++y) {
        for (int x = 0; x < size; ++x) {
            clues[y][x] = 0;
            discovered[y][x] = '*';
        }
    }

    while(bombs>0){
        int randomY = rand() % size;
        int randomX = rand() % size;
        //printf("Y:%d - X:%d",randomY,randomX);

        if(clues[randomY][randomX] > -1){
            clues[randomY][randomX] = -1;

            for (int y = -1; y <= 1; ++y) {
                if(randomY+y < 0 || randomY+y > size){
                    continue;
                }
                for (int x = -1; x <= 1 ; ++x) {
                    if(randomX+x < 0 || randomX+x > size){
                        continue;
                    }
                    if (clues[randomY+y][randomX+x] > -1) {
                        clues[randomY+y][randomX+x] += 1;
                    }
                }
            }
            bombs--;

            displayTest(size, clues);
            printf("\n___________\n");
        }
    }
}

void giveUserClues(int size, int clues[size][size], char discovered[size][size], int numClues){
    srand(time(0));
    while(numClues>0){
        int randomY = rand() % size;
        int randomX = rand() % size;
        //printf("Y:%d - X:%d - numClues:%d\n",randomY,randomX,numClues);


        if(clues[randomY][randomX] == -1 || discovered[randomY][randomX] != '*'){
            //printf("\tclues:%d\n\tdiscovered:%c\n\tBruh\n",clues[randomY][randomX],discovered[randomY][randomX]);
            continue;
        }
        else{
            discovered[randomY][randomX] = 48 + clues[randomY][randomX];
            numClues--;
        }
    }
}

int main(int argc, char *argv[]) {
    int cluesLeft = 3;
    int givenClues = 3;
    int bombs = 3;
    int size = 6;
    int clues[size][size];
    char discovered[size][size];
    bool giveClues = true;



    if(argv[1] != NULL){
        if(strcmp(argv[1],"clues")){
            giveClues = true;
        }
    }
    if(argv[2] != NULL ){
        int argvToInt = *argv[2];
        bombs = argvToInt;
    }


    generateClues(size,clues,discovered,bombs);

    if(giveClues){
        giveUserClues(size,clues,discovered,givenClues);
    }

    while(cluesLeft >= 0){
        display(size, discovered,cluesLeft);

        printf("\nEnter position (Y axis, X axis)");
        char y = get_user_char();
        char x = get_user_char();

        y -= 'A';
        x -= 'A';

        if(y<0 || y>=size){
            printf("\n*Invalid Y axis input*\n");
            continue;
        }
        if(x<0 || x>=size){
            printf("\n*Invalid X axis input*\n");
            continue;
        }

        /*add some error checking here*/
        bool check = guess(size, clues, discovered,y,x);
        if(!check){
            printf("\nYou lost ;_;");
            return 0;
        }
        cluesLeft--;
    }
    bool discoveredBombs = true;
    while(bombs>0 && discoveredBombs == true){
        display(size, discovered,bombs);
        printf("\nGuess bomb positions");
        printf("\n Enter position (Y axis, X axis)");

        char y = get_user_char();
        char x = get_user_char();
        y -= 'A';
        x -= 'A';

        if(guess(size, clues,discovered,y,x) != false) {
            discoveredBombs = false;
        }
        bombs -= 1;
    }
    if(discoveredBombs){
        printf("\n\n***YOU WON***");
    }
    else{
        printf("\n\n***YOU LOST***");
    }
    return 0;
}
