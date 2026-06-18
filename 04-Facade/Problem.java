/*When we push the power button on a computer, the computer must take the operating system files out of the 
permanent archive (HardDrive), and place them onto the workspace (Memory) so the CPU can run them. */

public class Problem {
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

    public static void main(String[] args) {
        Cpu cpu = new Cpu();
        Memory memory = new Memory();
        HardDrive hardDrive = new HardDrive();

        cpu.freeze();
        memory.load(0, hardDrive.read(0, 1024));
        cpu.jump();
        cpu.execute();
    }
}
