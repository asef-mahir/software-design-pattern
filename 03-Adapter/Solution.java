public class Solution {
    interface ILion {
        void roar();
    }

    static class AfricanLion implements ILion {
        public void roar() {
            System.out.println("Roar");
        }
    }

    static class WildDog {
        void bark() {
            System.out.println("Bark");
        }
    }

    static class WildDogAdapter implements ILion {
        private final WildDog dog;

        WildDogAdapter(WildDog dog) {
            this.dog = dog;
        }

        public void roar() {
            dog.bark();
        }
    }

    static class Hunter {
        void hunt(ILion lion) {
            lion.roar();
        }
    }

    public static void main(String[] args) {
        Hunter hunter = new Hunter();
        WildDog dog = new WildDog();
        ILion adaptedDog = new WildDogAdapter(dog);

        hunter.hunt(adaptedDog);             //Output: Bark
    }
}
