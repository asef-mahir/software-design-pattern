public class Problem {
    static class Chef {
        void cook(String food) {
            System.out.println("Cooking " + food);
        }
    }

    public static void main(String[] args) {
        Chef chef = new Chef();

        String order = "burger";
        if ("burger".equals(order)) {
            chef.cook("burger");
        } else if ("pizza".equals(order)) {
            chef.cook("pizza");
        } else if ("salad".equals(order)) {
            chef.cook("salad");
        }
    }
}
