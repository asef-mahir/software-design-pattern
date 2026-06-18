public class Solution {
    static class Cpu {
        void freeze() {
            System.out.println("CPU freeze");
        }

        void jump() {
            System.out.println("CPU jump");
        }

        void execute() {
            System.out.println("CPU execute");
        }
    }

    static class Memory {
        void load(long position, byte[] data) {
            System.out.println("Memory load");
        }
    }

    static class HardDrive {
        byte[] read(long lba, int size) {
            System.out.println("Hard drive read");
            return new byte[size];
        }
    }

    static class Computer {
        private final Cpu cpu = new Cpu();
        private final Memory memory = new Memory();
        private final HardDrive hardDrive = new HardDrive();

        void start() {
            cpu.freeze();
            memory.load(0, hardDrive.read(0, 1024));
            cpu.jump();
            cpu.execute();
        }

        void shutdown() {
            System.out.println("Computer shutdown");
        }
    }

    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.start();
        computer.shutdown();
    }
}



/*Boot Sequence in a nutshell: 
When we call computer.start(), it triggers this automated sequence:

cpu.freeze(); – Pause the processor while we prepare the system.

memory.load(0, hardDrive.read(0, 1024)); – Grab the operating system data from the Hard Drive and copy it into the RAM (Memory) workspace.

cpu.jump(); – Tell the CPU to point its focus directly to that spot in Memory where we just loaded the data.

cpu.execute(); – Tell the CPU to start running the operating system instructions!

Without the Computer Facade class, the user would have to manually call all 4 of those complex, low-level hardware steps themselves just to turn on the PC. Instead, the Facade gives them a nice, clean start() button. */