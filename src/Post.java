import java.util.ArrayList;
public class Post {
    // Post Condition Variables
    // Title
    private final int minTitleLength = 10;
    private final int maxTitleLength = 250;
    // Body
    private final int minBodyLength = 250;
    private final int minBodyDifficult = 300;
    // Tags
    private final int minNumTags = 2;
    private final int maxNumTags = 5;
    private final int maxNumTagsEasy = 3;
    private final int minTagLength = 2;
    private final int maxTagLength = 10;
    // Comments
    private final int minCommentWords = 4;
    private final int maxCommentWords = 10;
    private final int maxNumComments = 5;
    private final int maxNumCommentsEasy = 3;

    

    // Post instance Variables
    private int postID;
    private String postTitle;
    private String postBody;
    private String[] postTags;
    private String[] postTypes = {"Very Difficult", "Difficult", "Easy"};
    private String postType;
    private String postEmergencyType;
    private String[] postEmergency = {"Immediately Needed", "Highly Needed", "Ordinary"};
    private ArrayList <String> postComments = new ArrayList<>();

    Post(String postTitle, String postBody, String[] postTags, String postType, String postEmergencyType) {
        this.postTitle = postTitle;
        this.postBody = postBody;
        this.postTags = postTags;
        this.postType = postType;
        this.postEmergencyType = postEmergencyType;
    }

    /*
     * Add the information of a post to a TXT file
     * If the post's information meets the following conditions,
     *  the information should be added to a TXT file (e.g., post.txt), 
     *  and the addPost function should return true.
     * If the post's information does not meet the following conditions,
     *  the information should not be added to the TXT file,
     *  and the addPost function should return false.
     */
    /*
     * 
Condition 1. The post title should have a minimum of 10 characters and a maximum of 250 characters. Moreover, the first five characters should not be numbers and special characters. 
Condition 2. The post body should have a minimum of 250 characters. 
Condition 3. Each post should have a minimum of 2 tags and a maximum of 5 tags. 
Moreover, tags should have a minimum of 2 characters and a maximum of 10 characters 
and should not include any upper-case letters.
Condition 4. Regarding difficulty, the post type can be classified into 
"Very Difficult", "Difficult", or "Easy". "Easy" posts should not have more than 3 tags. 
"Very Difficult", "Difficult" posts should have a minimum of 300 characters in their body.
Condition 5. Regarding the emergency of a need for an answer, a post can be classified as 
"Immediately Needed", "Highly Needed", or "Ordinary". "Easy" posts should not have the "Immediately Needed" 
and "Highly Needed" statuses. "Very Difficult" and "Difficult" posts should not have the status of "Ordinary".
     */
    public boolean addPost() 
    {
        if (isValidTitle() && isValidBody() && isValidTags() && isValidType() && isValidEmergencyType()) {
            return true;
        }
        return false;
    }

    private boolean isValidTitle() {
        boolean isValid = true;
        if (this.postTitle.length() < minTitleLength) {   // Post Title should have a minimum of 10 characters
            isValid = false;
        } else if (this.postTitle.length() > maxTitleLength) { // Post Title should have a maximum of 250 characters 
            isValid = false;
        } else {                   // First 5 characters should not be numbers and special charcters
            String firstFiveChars = this.postTitle.substring(0,5);
            for (char c : firstFiveChars.toCharArray()) {
                if (!Character.isLetter(c) && c != ' ') {
                    isValid = false;
                }
            }
        }
        return isValid;
    }

    private boolean isValidBody() {
        boolean isValid = true;
        if (this.postBody.length() < minBodyLength) {  // Post Body should have a minimum of 250 characters
            isValid = false;
        } else if ((this.postType == "Very Difficult" || this.postType == "Difficult") && this.postBody.length() < minBodyDifficult) {
            isValid = false;
        }
        return isValid;
    }

    private boolean isValidTags() {
        boolean isValid = true;
        int numTags = this.postTags.length;
        
        // Checking the number of tags
        if (numTags < minNumTags) { // Minimum of 2 tags
            isValid = false;
        } else if (numTags > maxNumTags) {  // Maximum of 5 tags
            isValid = false;
        } else if (this.postType == "Easy" && numTags > maxNumTagsEasy) {   // Easy has maximum of 3 tags
            isValid = false;
        }

        // Checking the tags syntax
        for (String tag : this.postTags) {
            if(tag.length() < minTagLength) {   // Minimum tag length of 2 characters
                isValid = false;
            } else if (tag.length() > maxTagLength) {   // Maximum tag length of 10 characters
                isValid = false;
            } else if (tag.toLowerCase() != tag) {    // Tag cannot contain upercase letters
                isValid = false;
            }
        }
        return isValid;
    }

    private boolean isValidType() {
        boolean isValid = false;
        for (int i = 0; i < postTypes.length; i++) {
            if (this.postType == this.postTypes[i]) {
                isValid = true;
            }
        }
        return isValid;
    }

    private boolean isValidEmergencyType() {
        boolean isValid = false;
        for (int i = 0; i < postEmergency.length; i++) {        // Check the emergency type is in our database
            if (this.postEmergencyType == this.postEmergency[i]) {
                isValid = true;
            }
        }
        if (this.postType.equals("Easy") && !this.postEmergencyType.equals("Ordinary")) {
            isValid = false;
        } else if ((this.postType.equals("Difficult") || this.postType.equals("Very Difficult")) && 
                    this.postEmergencyType.equals("Ordinary")) {
            isValid = false;
        }
        return isValid;
    }
    /*
     * Add the information of a comment under a post to a TXT file
     * If the comment's information meets the following conditions,
     *  the information should be added to a TXT file (e.g., comment.txt), 
     *  and the addComment function should return true.
     * If the comment's information does not meet the followina conditions
     *  the information should not be added to the TXT file, 
     *  and the addComment function should return false.
     */
    public boolean addComment(String comment)
    {
        String[] wordsInComment = comment.split(" ");
        boolean isAddCommentValid = true;
        if (wordsInComment.length < minCommentWords || wordsInComment.length > maxCommentWords) {      
            isAddCommentValid = false;  // Comment min 4 words and max 10 words
        } else if (!Character.isLetter(wordsInComment[0].charAt(0))) {   
            isAddCommentValid = false;  // Comment must start with a letter
        } else if (wordsInComment[0].charAt(0) != Character.toUpperCase(wordsInComment[0].charAt(0))) { 
            isAddCommentValid = false;  // Comment first character must be upper case
        } else if (postComments.size() >= maxNumComments) {
            isAddCommentValid = false;  // Max number of comments already on post
        } else if ((this.postType.equals("Ordinary") || this.postType.equals("Easy"))
                    && postComments.size() >= maxNumCommentsEasy) {
            isAddCommentValid = false;  // Max number of comments already for easy/ordinary post
        }

        if (isAddCommentValid) {
            putCommentInDataBase(comment);
        }

        return isAddCommentValid;
    }

    private void putCommentInDataBase(String comment) {
        postComments.add(comment);
    }
}
