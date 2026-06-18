public class Problem {
    interface ILion {
        void roar();
    }

    static class AfricanLion implements ILion {
        public void roar() {
            System.out.println("Roar");
        }
    }

    static class Hunter {
        void hunt(ILion lion) {
            lion.roar();
        }
    }

    static class WildDog {
        void bark() {
            System.out.println("Bark");
        }
    }

    public static void main(String[] args) {
        Hunter hunter = new Hunter();

        // hunter.hunt(new WildDog()); // does not work: different interface
        hunter.hunt(new AfricanLion());
    }
}
