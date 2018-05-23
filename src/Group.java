public class Group {
    private String name;
    private int questionid;
    private char[] answers[10];

    public Group(String name){
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Boolean enterSecret(String code) {
        if(code.contentEqual("henk")) {
            this.questiondid = 1;
        }
    }

    public Boolean answerQuestion(char answer) {
        this.answers[this.questionid] = answer;
        return false;  //Answer was wrong
    }
}
