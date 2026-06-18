public class Problem {
    static class Sheep {
        String name;
        String category;

        Sheep(String name, String category) {
            this.name = name;
            this.category = category;
        }
    }

    public static void main(String[] args) {
        Sheep original = new Sheep("Jolly", "Mountain Sheep");

        Sheep cloned = new Sheep(original.name, original.category);
        cloned.name = "Dolly";

        System.out.println(original.name);
        System.out.println(original.category);
        System.out.println(cloned.name);
        System.out.println(cloned.category);
    }
}
