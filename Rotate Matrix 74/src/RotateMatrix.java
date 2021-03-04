public class RotateMatrix {

    /**
     * Rotate Matrix:
     * Given a NxN matrix, rotate the matrix clock-wise by 90 degrees
     *
     * - Problem: Cracking the coding interview problem 1.7 , page 91
     * */

    public static void main(String[] args) {
        boolean rotate = rotateMatrix(
                new int[][]{
                        {1, 1, 1, 1},
                        {2, 2, 2, 2},
                        {3, 3, 3, 3},
                        {4, 4, 4, 4}
                }
        );

        System.out.print("\n Matrix rotated: " + rotate);
    }

    public static boolean rotateMatrix(int[][] matrix) {

        //Validate NxN matrix
        if (matrix.length != matrix[0].length) return false;

        int n = matrix.length;
        /*
         * Layer loop
         * We iterate until n < 2 layers, because it is a NxN matrix, so if we go from the outer layer inward
         * then we should scan half the times the size of the matrix.
         * */
        for (int layer = 0; layer < n / 2; layer++) {

            /*
            * When we rotate a matrix by 90 degrees, we are moving element by element, meaning that
            * element in [0][0] top left will have to move to [0][3] top right, which is 3 positions.
            * The "last" element to move should have index (matrix.length - 1 - layer)
            * */
            int last = n - 1 - layer;

            /*
            * Move elements of the matrix clockwise by 90 degrees
            * */
            for (int i = layer; i < last; i++) {
                int offset = i - layer;
                //save top
                int top = matrix[layer][i];
                //left -> top
                matrix[layer][i] = matrix[last - offset][layer];
                //bottom -> left
                matrix[last - offset][layer] = matrix[last][last - offset];
                //right -> bottom
                matrix[last][last - offset] = matrix[i][last];
                //top -> right
                matrix[i][last] = top;
            }

        }

        //Print matrix
        printMatrix(matrix);

        return true;
    }

    /*
    * Print matrix by column
    * */
    public static void printMatrix(int[][] matrix) {
        System.out.print("\n");
        for (int[] ints : matrix) {
            for (int column = 0; column < matrix.length; column++) {
                System.out.print(" " + ints[column] + " ");
            }
            System.out.print("\n");
        }
    }

}
