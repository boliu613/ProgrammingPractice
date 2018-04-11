package student;

public class Student {
	//instance variable
	String name;
	double totalScore;
	double averageScore;
	int homeworkNum;

	/**
	 * constructor
	 */
	public Student(String name) {
		this.name = name;
	}
	
	/**
	 * get the name of the student
	 * @return
	 */
	String getName(){
		return this.name;
	}
	
	/**
	 * add a homework score to the record 
	 * and modify the total score and average score of the student
	 * @param score
	 */
	void addHWScore(double score){
		this.totalScore += score;
		this.homeworkNum += 1;
		this.averageScore = this.totalScore/homeworkNum;
	}
	
	/**
	 * get the total score of the student
	 * @return
	 */
	double getTotalScore(){
		return this.totalScore;
	}
	
	/**
	 * get the average score of the student
	 * @return
	 */
	double getAverageScore(){
		return this.averageScore;
	}
	
	@Override
	public String toString() {
		return "Name: " + this.name + '\n' +  "Total Score: " 
				+ this.totalScore + '\n' + "Average Score: " + this.averageScore; 
	}

}
