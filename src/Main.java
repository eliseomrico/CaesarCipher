import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static char[] alphabet = {
            'a',
            'b',
            'c',
            'd',
            'e',
            'f',
            'g',
            'h',
            'i',
            'j',
            'k',
            'l',
            'm',
            'n',
            'o',
            'p',
            'q',
            'r',
            's',
            't',
            'u',
            'v',
            'w',
            'x',
            'y',
            'z'
    };

    public static Scanner input = new Scanner(System.in);

    // Input Functions
    public static String get_user_text(String prompt){
        String text;

        System.out.printf("%s",prompt);
        text = input.nextLine().toLowerCase();
        return text;
    }
    public static int get_user_key(){
        int key = -1;

        while (key <= 0) {
            System.out.print("Please enter a positive integer greater than 0 for the key: ");
            key = input.nextInt();
            if (key <= 0) {
                System.out.println("\nError: Please enter a positive integer greater than 0");
            }
        }
        return key;
    }

    //Output Function
    public static void encryptionOutput(String plaintext, String encryptedText){
        System.out.printf("\n     Plaintext: %s",plaintext);
        System.out.printf("\nEncrypted Text: %s\n",encryptedText);
    }
    public static void decryptionOutput(String plaintext, String encryptedText){
        System.out.printf("\nEncrypted Text: %s\n",encryptedText);
        System.out.printf("     Plaintext: %s",plaintext);
    }

    // Helper Functions
    public static int findCharacterIdx(char letter, char[] alphabet){

        for(int i=0;i<alphabet.length;i++){
            if(alphabet[i] == letter){
                return i;
            }
        }

        return 25;
    }

    // ** Encryption **
    public static void encryptText(String plaintext, int key){

        char[] plaintext_arr = plaintext.toCharArray();
        char letter;
        String ciphertext = "";
        int plaintext_idx, ciphertext_idx;

        for(int i=0;i<plaintext_arr.length;i++){
            // Set current letter
            letter = plaintext_arr[i];

            if(letter == ' '){
                ciphertext += " ";
            }else{
                // Get Index of current letter
                plaintext_idx = findCharacterIdx(letter,alphabet);
                ciphertext_idx = (plaintext_idx + key) % 26;

                ciphertext += alphabet[ciphertext_idx];
            }
        }
        encryptionOutput(plaintext,ciphertext);

    }

    // ** Decryption **
    public static void decryptText(String ciphertext, int key){

        char[] ciphertext_arr = ciphertext.toCharArray();
        char letter;
        int plaintext_idx=0, ciphertext_idx;
        String plaintext = "";

        for(int i=0;i<ciphertext_arr.length;i++){
            // Set current letter
            letter = ciphertext_arr[i];

            if(letter == ' '){
                plaintext += " ";
            }else {
                // Get Index of current letter
                ciphertext_idx = findCharacterIdx(letter, alphabet);
                plaintext_idx = (((ciphertext_idx - key) % 26) + 26) % 26;
                plaintext += alphabet[plaintext_idx];
            }
        }
        decryptionOutput(plaintext,ciphertext);

    }

    public static void main(String[] args) {

        // Encryption
        System.out.println("\nEncryption");
        System.out.println("==========");
        String plaintext = get_user_text("\nPlease enter plaintext to encrypt: ");
        int encryption_key = get_user_key();

        encryptText(plaintext,encryption_key);


        // Decryption
        System.out.println("\n\nDecryption");
        System.out.println("========");
        input.nextLine();
        String ciphertext = get_user_text("\nPlease enter plaintext to decrypt: ");
        int decryption_key = get_user_key();

        decryptText(ciphertext,decryption_key);

    }
}