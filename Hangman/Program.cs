using System.Net;
using System.Reflection.Metadata.Ecma335;

class HangMan
{
    
    public static void Story()
    {
        Console.WriteLine("What is your name?");
        Console.Write("Name: ");
        string playerName = Console.ReadLine();
        Console.Clear();

        Console.WriteLine(playerName + " Your are being hung at the kings mercy");
        Console.WriteLine("The king is a word freak and will only let");
        Console.WriteLine("you live if you can guess what word he is thinking of.");
    }
    
    
    public static void PrintHangMan(int mistake)
    {
        Console.WriteLine("");
        if (mistake > 0) { Console.WriteLine("                          O"); }
        if (mistake == 2) { Console.Write("                          I"); }
        if (mistake >= 3) { Console.Write("                        --I"); }
        if (mistake >= 4) { Console.WriteLine("--"); }
        if (mistake >= 5) { Console.Write("                         /"); }
        if (mistake >= 6) { Console.Write(" \\"); }
        
        Console.WriteLine("");
        
    }
    
    
    
    
    
    
    //Makes sure guess is Original.
    public static bool LetterIsOriginal(char guess, int guessAmount, char[] originalGuessArray )
    {
       

        originalGuessArray[guessAmount] = guess;

        //return false if their is a repeat. 
        for (int i = 0; i < guessAmount; i++)
        {
            if (guess == originalGuessArray[i]) { return false; }
        }
        return true;

    }
    
    //Gets word user is trying to guess
    public static string SecretWord()
    {
        WebClient web = new WebClient();
        string listOfWords = web.DownloadString("https://www.mit.edu/~ecprice/wordlist.10000");
        string[] wordBank = listOfWords.Split('\n');
        
        Random random = new Random();
        int randomIndex = random.Next(1, 1000);
        return wordBank[randomIndex];
    }

    //Method for guessing a specific character in the array.
    public static int GuessLetter(char[] secretWordArray, char[] guessArray, int guessAmount, char[] originalGuessArray)
    {
        int charCounter = 0;
        int mistake = 0;
      
        char guess = '-';
        int wordLength = secretWordArray.Length;
        bool guessIsOriginal = false;
        

        //Get guess from user and makes sure it is a letter.
        while (!Char.IsLetter(guess) && !guessIsOriginal)
        {


            //Show letters guessed already
            if (guessAmount > 0)
            {
                Console.Write("Letters already guessed:    ");
                for (int i = 0; i < guessAmount; i++)
                {
                    Console.Write(originalGuessArray[i] + " ");
                }
                Console.WriteLine();
            }

            Console.Write("Guess a Letter: ");
           guess = Console.ReadKey().KeyChar;
            Console.WriteLine();



            //if guess was not a letter tell user to try again.
            if (!Char.IsLetter(guess))
            {
                Console.WriteLine("Guess was not a letter please try again.");
            }
            
            //Check to make sure letter is not a repeat;
            else
            {
                guessIsOriginal = LetterIsOriginal(guess, guessAmount, originalGuessArray);

                if (!guessIsOriginal) { Console.WriteLine("You have already guessed " + guess + " please try again."); }
            }
        }
        
        //Check to see if guess is in array and if so put it it the guess array.
        for (int i = 0; i < wordLength; i++)
        {
            if (secretWordArray[i] == guess)
            {
                guessArray[i] = guess;
                charCounter++;
            }
            else
            {
                mistake++;
            }
        }

       
        //Display how many of that letter are in the word then display array.
        Console.Write("There are " + charCounter + " " + guess + "   ");
        for (int i = 0; i < wordLength; i++)
       {
           Console.Write(guessArray[i]);
       }
        Console.WriteLine();
       

        //if guess is not in array return 1 so we know their guess was wrong.
         if(wordLength == mistake) { return 1; }
        else { return 0; }  

    }



    static void Main(string[] args)
    {
        //Story which is irrevent
        Story();
        
        string secretWord = SecretWord();
        int mistake = 0;
        int guessAmount = 0;
        bool guessIsWrong = true;

        int wordLength = secretWord.Length;
        //Console.WriteLine(wordLength);

        char[] guessArray = new char[wordLength];
        char[] originalGuessArray = new char[26];

        //Make guesArray full of blanks
        for (int i = 0; i < wordLength; i++)
        {
            guessArray[i] = '_';
        }

        //Print out array for the first time
        for (int i = 0; i < wordLength; i++)
        {
            Console.Write(guessArray[i]);
        }

        Console.WriteLine("   There are " + wordLength + " Letters.");

        char[] secretWordArray = secretWord.ToCharArray();

        while (mistake < 7 && guessIsWrong) 
        {
            mistake += GuessLetter(secretWordArray, guessArray, guessAmount, originalGuessArray);
            guessAmount++;

            guessIsWrong = false;

            for(int i = 0;i < wordLength; i++)
            {
                if (guessArray[i] == '_') 
                { 
                    guessIsWrong = true;
                    i = wordLength;                
                }
            }
 

            PrintHangMan(mistake);

        }

        //player loses
        if (mistake >= 7) 
        { 
            Console.WriteLine("You lose the word was " + secretWord + "says the king as he pulls the lever with a grin on his face"); 
        }
        //player wins
        if (!guessIsWrong) 
        { 
            Console.WriteLine("You shall live to see another day says the king in disappointment"); 
        }
        Console.ReadLine();

    }
}

