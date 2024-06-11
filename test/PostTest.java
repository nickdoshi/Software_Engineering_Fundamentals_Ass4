import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PostTest {
    /*
     * Testing Variables
     */
    String validTitle = "Valid Title 123";
    String validBody = """
        There is currently no maximum length for question body. 
        So I could yap the whole works of Shakespeare and every coding documentation 
        known to humans inside this body. This will be an upper limit length test on. 
        Java Strings can hold a maximum of 2,147,483,647 characters. I don’t anticipate 
        us ever getting to that. The average word length is 4.7 characters then with a 
        space that is 5.7 characters per word.  Therefore a Java String can hold a 
        maximum of 376,751,517 words on average. This is so many words that I could 
        include every play Shakespeare ever wrote and the whole Harry Potter series… 
        190 times. Safe to say we should never hit the maximum limit :)
        """;
    String[] validTags = new String[]{"python", "code"};
    String validComment = "This is a valid comment.";
    String invalidComment = "invalid comment";
    /*
     * Tests for addPost()
     */
    @Test
    public void testAddPost_tc1() {
        // Test Case 1 : Check the function with valid inputs
            // Test Case 1_Test Data 1
            Post postTestCase1Data1 =  new Post("My first question about Coding", validBody,
                                            new String[]{"beginner", "university", "mark"},
                                            "Easy", "Ordinary");
            assertTrue(postTestCase1Data1.addPost());
            // Test Case 1_Test Data 2
            Post postTestCase1Data2 = new Post("Hell 0 Second Question",  """
            								THIS IS THE SECOND QUESTION TEST. THIS IS A VERY LOUD 
            								TEST AND A VERY VERY VERY VERY VERY VERY VERY DIFFICULT 
            								QUESTION. This is the worlds hardest coding problem. This 
            								problem has stumped professors for decades, please kindly 
            								write your responses in the comments if you have time. 
            		                      	What does grass look like?
            								""", 
            								new String[]{"grass", "impossible", "innovation", "pi", "hi"}, 
            								"Very Difficult", "Immediately Needed");
            assertTrue(postTestCase1Data2.addPost());
            // Test Case 1_Test Data 3
            Post postTestCase1Data3 = new Post("min title!", validBody,  
                                    		new String[]{"11", "22", "numbers", "string"}, 
                                    		"Difficult", "Highly Needed");
            assertTrue(postTestCase1Data3.addPost());
    }

    @Test
    public void testAddPost_tc2() {
        // Test Case 2 : Check the function with an invalid title
            // Test Case 2_Test Data 1  :  Start with special chars/numbers
            Post postTestCase2Data1 = new Post("!2!45invalid", validBody,  validTags, "Difficult", "Highly Needed");
            assertFalse(postTestCase2Data1.addPost());
            // Test Case 2_Test Data 2  :  Title too short
            Post postTestCase2Data2 = new Post("short", validBody, validTags, "Difficult", "Highly Needed");
            assertFalse(postTestCase2Data2.addPost());
            // Test Case 2_Test Data 3  :  Title too long
            Post postTestCase2Data3 = new Post(validBody,  validBody, validTags, "Difficult", "Highly Needed");
            assertFalse(postTestCase2Data3.addPost());
    }

    @Test
    public void testAddPost_tc3() {
        // Test Case 3 : Check the function with an invalid post body
            // Test Case 3_Test Data 1  :  Body less than 250 chars
            Post postTestCase3Data1 = new Post(validTitle, "Short Body", validTags, "Easy", "Ordinary");
            assertFalse(postTestCase3Data1.addPost());
            // Test Case 3_Test Data 2  :  Body less than 300 chars
            String body299 = new String(new char[299]).replace('\0', 'A');
            Post postTestCase3Data2 = new Post(validTitle, body299, validTags, "Difficult", "Highly Needed");
            assertFalse(postTestCase3Data2.addPost());
    }

    @Test
    public void testAddPost_tc4() {
        // Test Case 4 : Check the function with invalid tags
            // Test Case 4_Test Data 1  :  Contains Upper Case, Tag length too short
            Post postTestCase4Data1 = new Post(validTitle, validBody, new String[]{"Python", "c"}, "Easy", "Ordinary");
            assertFalse(postTestCase4Data1.addPost());
            // Test Case 4_Test Data 2  :  Tag Length over 10 chars
            Post postTestCase4Data2 = new Post(validTitle, validBody, new String[]{"tagistoolong", "python", "xyz"}, "Easy", "Ordinary");
            assertFalse(postTestCase4Data2.addPost());
            // Test Case 4_Test Data 3  :  Too many tags for Easy difficulty
            Post postTestCase4Data3 = new Post(validTitle, validBody, new String[]{"tags", "python", "xyz", "lmno"}, "Easy", "Ordinary");
            assertFalse(postTestCase4Data3.addPost());
    }

    @Test
    public void testAddPost_tc5() {
        // Test Case 5 : Check the function with an invalid postEmergency
            // Test Case 5_Test Data 1  :  Invalid postEmergency for Easy
            Post postTestCase5Data1 = new Post(validTitle, validBody, validTags, "Easy", "Immediately Needed");
            assertFalse(postTestCase5Data1.addPost());
            // Test Case 5_Test Data 2  :  Invalid postEmergency for Very Difficult
            Post postTestCase5Data2 = new Post(validTitle, validBody, validTags, "Very Difficult", "Ordinary");
            assertFalse(postTestCase5Data2.addPost());
    }

    @Test
    public void testAddPost_tc6() {
        // Test Case 6 : Check the function with an invalid postType
            // Test Case 6_Test Data 1  :  Invalid Type
            Post postTestCase6Data1 = new Post(validTitle, validBody, validTags, "Hard", "Ordinary");
            assertFalse(postTestCase6Data1.addPost());
            // Test Case 6_Test Data 2  :  Invalid Type
            Post postTestCase6Data2 = new Post(validTitle, validBody, validTags, "Moderate", "Highly Needed");
            assertFalse(postTestCase6Data2.addPost());
    }

    /*
     * Tests for addComment()
     */

    @Test
    public void testAddComment_tc1() {
        //  Test Case 1: Check the function with valid inputs
            //  Test Case 1_Test Data 1 : Valid Inputs
            Post commentTestCase1Data1 = new Post(validTitle, validBody, validTags, "Easy", "Ordinary");
            assertTrue(commentTestCase1Data1.addComment(validComment));
            //  Test Case 1_Test Data 2 : Valid Inputs
            Post commentTestCase1Data2 = new Post(validTitle, validBody, validTags, "Easy", "Ordinary");
            commentTestCase1Data2.addComment(validComment);
            assertTrue(commentTestCase1Data2.addComment("You need to create a string"));
    }

    @Test
    public void testAddComment_tc2() {
        //  Test Case 2: Check the function with invalid comment text length
            //  Test Case 2_Test Data 1 : Comment Length Too Short
            Post commentTestCase2Data1 = new Post(validTitle, validBody, validTags, "Very Difficult", "Urgently Needed");
            assertFalse(commentTestCase2Data1.addComment("Invalid Comment."));
            //  Test Case 2_Test Data 2 : Comment Length Too Long
            Post commentTestCase2Data2 = new Post(validTitle, validBody, validTags, "Very Difficult", "Urgently Needed");
            assertFalse(commentTestCase2Data2.addComment("This comment is invalid because it is so long that no one would care to read all of it."));
    }

    @Test
    public void testAddComment_tc3() {
        //  Test Case 3: Check the function with invalid capitalisation for first word
            //  Test Case 3_Test Data 1 : Invalid Capitalisation
            Post commentTestCase3Data1 = new Post(validTitle, validBody, validTags, "Very Difficult", "Urgently Needed");
            assertFalse(commentTestCase3Data1.addComment("this comment has invalid Comment Capitalisation."));
            //  Test Case 3_Test Data 2 : First Character must be Upper Case
            Post commentTestCase3Data2 = new Post(validTitle, validBody, validTags, "Very Difficult", "Urgently Needed");
            assertFalse(commentTestCase3Data2.addComment("1 st: This Comment Also Has Invalid Capitalisation."));
    }

    @Test
    public void testAddComment_tc4() {
        //  Test Case 4: Check the function for comment, but exceeds limit for easy/ordinary Post
            //  Test Case 4_Test Data 1 : Comment Limit Exceeded for Easy/Ordinary Post
            Post commentTestCase4Data1 = new Post(validTitle, validBody, validTags, "Easy", "Ordinary");
            commentTestCase4Data1.addComment(validComment);
            commentTestCase4Data1.addComment(validComment);
            commentTestCase4Data1.addComment(validComment);
            assertFalse(commentTestCase4Data1.addComment(validComment));
            //  Test Case 4_Test Data 2 : Comment Limit Exceeded for Easy/Ordinary Post
            Post commentTestCase4Data2 = new Post(validTitle, validBody, validTags, "Easy", "Ordinary");
            commentTestCase4Data2.addComment(validComment);
            commentTestCase4Data2.addComment(validComment);
            commentTestCase4Data2.addComment(validComment);
            assertFalse(commentTestCase4Data2.addComment(invalidComment));
    }

    @Test
    public void testAddComment_tc5() {
        // Test Case 5: Check the function for valid comment, but exceeds limit for Difficult/Very Difficult Post
            //  Test Case 5_Test Data 1 : Valid Inputs
            Post commentTestCase5Data1 = new Post(validTitle, validBody, validTags, "Difficult", "Immediately Needed");
            for (int i = 0; i < 5; i++) {
                commentTestCase5Data1.addComment(validComment);
            }
            assertFalse(commentTestCase5Data1.addComment(validComment));
            //  Test Case 5_Test Data 2 : Valid Inputs
            Post commentTestCase5Data2 = new Post(validTitle, validBody, validTags, "Very Difficult", "Highly Needed");
            for (int i = 0; i < 5; i++) {
                commentTestCase5Data2.addComment(validComment);
            }
            assertFalse(commentTestCase5Data2.addComment(validComment));
    }

    @Test
    public void testAddComment_tc6() {
        // Test Case 6: Check the function for invalid comment, but exceeds limit for Difficult/Very Difficult Post
            //  Test Case 6_Test Data 1 : Valid Inputs
            Post commentTestCase6Data1 = new Post(validTitle, validBody, validTags, "Difficult", "Highly Needed");
            for (int i = 0; i < 5; i++) {
                commentTestCase6Data1.addComment(validComment);
            }
            assertFalse(commentTestCase6Data1.addComment(invalidComment));
            //  Test Case 6_Test Data 2 : Valid Inputs
            Post commentTestCase6Data2 = new Post(validTitle, validBody, validTags, "Very Difficult", "Immediately Needed");
            for (int i = 0; i < 5; i++) {
                commentTestCase6Data2.addComment(validComment);
            }
            assertFalse(commentTestCase6Data2.addComment(invalidComment));
    }

}