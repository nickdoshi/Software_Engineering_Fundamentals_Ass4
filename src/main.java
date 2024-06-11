import java.util.Scanner;
public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        PostTest pt = new PostTest();

        Post postTestCase1Data1 =  new Post("My first question about Coding", pt.validBody,
        new String[]{"beginner", "university", "mark"},
        "Easy", "Ordinary");
        postTestCase1Data1.addPost();

        System.out.println("Post has been loaded into the data base! Are ready to load comments?");
        String input1 = sc.nextLine();
        if (input1.equals("yes")) {
            postTestCase1Data1.addComment("Wow! Great Question, let me have a think");
            postTestCase1Data1.addComment("Convert integer to long");
            postTestCase1Data1.addComment("I agree with the above comment");
            System.out.println("Added 3 comments to the bottom of the post");
        }
        
    }
}
