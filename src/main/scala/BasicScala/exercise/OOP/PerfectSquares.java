package BasicScala.exercise.OOP;


// Driver Code
public class PerfectSquares {
    public static void main(String[] args) {
        int A = 10;
        int B = 20;
        System.out.println(solution(A, B));
    }

    public static int solution(int A, int B) {
        if (A > B) {
            return 0;
        }
        int sqrtA = (int)Math.ceil(Math.sqrt(A));
        int sqrtB = (int)Math.floor(Math.sqrt(B));
        if (sqrtA > sqrtB) {
            return 0;
        }
        return 1 + solution(sqrtA, sqrtB);
    }
}
