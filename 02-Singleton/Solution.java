public class Solution {
    static class President {
        private static President instance;

        private President() {
        }

        static President getInstance() {
            if (instance == null) {
                instance = new President();
            }
            return instance;
        }
    }

    public static void main(String[] args) {
        President first = President.getInstance();
        President second = President.getInstance();

        System.out.println(first == second);
    }
}
