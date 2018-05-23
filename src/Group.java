public class Group {
    private String name;
    private int questionid;
    private char[] answers = new char[10];

    public Group(String name){
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Boolean enterSecret(String code) {
        if(code.contentEquals("henk")) {
            this.questionid = 1;
        }
		return false;	//Wrong secret
    }

    public Boolean answerQuestion(char answer) {
        this.answers[this.questionid] = answer;
        return false;  //Answer was wrong
    }
}
