public class Problem {
    static class President {
        President() {
        }
    }

    public static void main(String[] args) {
        President first = new President();
        President second = new President();

        System.out.println(first == second);
    }
}
