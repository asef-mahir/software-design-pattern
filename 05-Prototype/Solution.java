public class Solution {
    // Cloneable interface is built into Java (java.lang.Cloneable), we don't have to define it ourself 
    // it just gives the permission to clone, doesn't clone itself
    static class Sheep implements Cloneable {                       
        String name;
        String category;

        Sheep(String name, String category) {
            this.name = name;
            this.category = category;
        }

        //If Sheep did not implement Cloneable, then super.clone() would fail with CloneNotSupportedException
        Sheep cloneSheep() {
            try {
                return (Sheep) super.clone();               // clone() comes from Java’s built-in Object class.
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }
    }

    public static void main(String[] args) {
        Sheep original = new Sheep("Jolly", "Mountain Sheep");
        Sheep cloned = original.cloneSheep();
        cloned.name = "Dolly";

        System.out.println(original.name);
        System.out.println(original.category);
        System.out.println(cloned.name);
        System.out.println(cloned.category);
    }
}



// Cloneable says “this object may be cloned,” and Object.clone() does the actual copying.