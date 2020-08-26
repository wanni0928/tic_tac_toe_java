# Tic Tac Toe project
## Project summary
1. Board
    - 3 x 3
2. Player
    - human player
    - AI player
3. Winner Judgement
    - case01. 3 stones horizontally
    - case02. 3 stones vertically
    - case03. 3 stones diagonally

## Summary
1. Input Integer N
2. Input whether player is AI or not.
3. If one of player get N win, game will terminated.. until then game will continue.
4. Follow the rules of the existing game.

## Strategy

### class
1. Board
    - Board has an array of gameBoard and Position.
    - The position basically follows the principle of the quadratic function.
2. Game
    - The Game `IS-A` GameSystem.
    - The Game `IS-A` GameService
    - GameSystem
        - It has the logic needed when the program is held or terminated.
    - GameService
        - It contains all the services provided by the game.
3. Main
    - It's a driver.
4. Player
    - The player `IS-A` Playable.
    - The player is basically human.
    - The AIPlayer `HAS-A` Player

### Game Process
1. First, start the game with MainDriver.
2. As a condition for the end of the game, the program asks for the number of victory limits(N).
3. The program will ask some queries if the player is an AI or not.
4. There are two playable players, and each turn, a stone is placed in the desired position.
5. If there is a stone already located, the program will continue to ask until the appropriate location is called.
6. When the stone is released, the program first checks to see if there are any horizontal connections.
7. If there is a continuation, the player is given 1 point and the game resets, but if not, it will check if there is a vertical continuation.
8. If the program can't find it in the vertical direction, it will check if there's anything going diagonally.
9. The appearance of what was found diagonally, the game continues, and the next player is given a chance to place the stone.
10. The process from 5 to 9 will be repeated until either player wins N times.

### UML
![UML DIAGRAM](https://github.com/wanni0928/tic_tac_toe_java/blob/master/asset/images/uml.gif)