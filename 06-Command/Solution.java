public class Solution {
    interface Command {
        void execute();
    }

    static class Chef {
        void cook(String food) {
            System.out.println("Cooking " + food);
        }
    }

    static class CookBurgerCommand implements Command {
        private final Chef chef;

        CookBurgerCommand(Chef chef) {
            this.chef = chef;
        }

        public void execute() {
            chef.cook("burger");
        }
    }

    static class Waiter {
        void takeOrder(Command command) {
            command.execute();
        }
    }

    public static void main(String[] args) {
        Chef chef = new Chef();
        Waiter waiter = new Waiter();
        waiter.takeOrder(new CookBurgerCommand(chef));
    }
}
