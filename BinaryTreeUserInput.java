import java.util.Scanner;

class Node {
    int data;
    Node left, right;

    // Construtor para o nó
    public Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class BinaryTreeUserInput {
    private Node root;

    // Método de inserção
    public void insert(int value) {
        root = insertRec(root, value);
    }

    // Método recursivo de inserção
    private Node insertRec(Node root, int value) {
        if (root == null) return new Node(value);
        if (value < root.data) root.left = insertRec(root.left, value);
        else root.right = insertRec(root.right, value);
        return root;
    }

    // Percurso em ordem
    public void inorder() {
        inorderRec(root);
        System.out.println();
    }

    // Método recursivo para o percurso em ordem
    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.data + " ");
            inorderRec(root.right);
        }
    }

    // Percurso pré-ordem
    public void preorder() {
        preorderRec(root);
        System.out.println();
    }

    // Método recursivo para o percurso pré-ordem
    private void preorderRec(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preorderRec(root.left);
            preorderRec(root.right);
        }
    }

    // Percurso pós-ordem
    public void postorder() {
        postorderRec(root);
        System.out.println();
    }

    // Método recursivo para o percurso pós-ordem
    private void postorderRec(Node root) {
        if (root != null) {
            postorderRec(root.left);
            postorderRec(root.right);
            System.out.print(root.data + " ");
        }
    }

    // Método para calcular a altura da árvore
    public int height() {
        return heightRec(root);
    }

    private int heightRec(Node node) {
        if (node == null) return -1;
        return 1 + Math.max(heightRec(node.left), heightRec(node.right));
    }

    // Método para obter o nível de um nó
    public int getLevel(int value) {
        return getLevelRec(root, value, 0);
    }

    private int getLevelRec(Node node, int value, int level) {
        if (node == null) return -1;
        if (node.data == value) return level;
        int downlevel = getLevelRec(node.left, value, level + 1);
        if (downlevel != -1) return downlevel;
        return getLevelRec(node.right, value, level + 1);
    }

    // Método para obter o grau de um nó
    public int getDegree(int value) {
        Node node = findNode(root, value);
        if (node == null) return -1;
        int degree = 0;
        if (node.left != null) degree++;
        if (node.right != null) degree++;
        return degree;
    }

    private Node findNode(Node root, int value) {
        if (root == null || root.data == value) return root;
        if (value < root.data) return findNode(root.left, value);
        return findNode(root.right, value);
    }

    // Método para buscar um nó
    public boolean search(int value) {
        return findNode(root, value) != null;
    }

    // Método para verificar se a árvore é estritamente binária
    public boolean isStrictlyBinary(Node node) {
        if (node == null) return true;
        if (node.left == null && node.right == null) return true;
        if (node.left != null && node.right != null)
            return isStrictlyBinary(node.left) && isStrictlyBinary(node.right);
        return false;
    }

    // Método para verificar se a árvore é completa
    public boolean isComplete() {
        return isCompleteRec(root, 0, countNodes(root));
    }

    private boolean isCompleteRec(Node node, int index, int numberNodes) {
        if (node == null) return true;
        if (index >= numberNodes) return false;
        return isCompleteRec(node.left, 2 * index + 1, numberNodes) &&
               isCompleteRec(node.right, 2 * index + 2, numberNodes);
    }

    private int countNodes(Node node) {
        if (node == null) return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    // Método para verificar se a árvore é cheia
    public boolean isFull(Node node) {
        if (node == null) return true;
        if (node.left == null && node.right == null) return true;
        if (node.left != null && node.right != null)
            return isFull(node.left) && isFull(node.right);
        return false;
    }

    // Método para remover um nó
    public void remove(int value) {
        root = removeRec(root, value);
    }

    private Node removeRec(Node root, int key) {
        if (root == null) return root;
        if (key < root.data) root.left = removeRec(root.left, key);
        else if (key > root.data) root.right = removeRec(root.right, key);
        else {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;
            root.data = minValue(root.right);
            root.right = removeRec(root.right, root.data);
        }
        return root;
    }

    private int minValue(Node root) {
        int minv = root.data;
        while (root.left != null) {
            minv = root.left.data;
            root = root.left;
        }
        return minv;
    }

    // Método principal
    public static void main(String[] args) {
        BinaryTreeUserInput tree = new BinaryTreeUserInput();
        Scanner scanner = new Scanner(System.in);

        // Inserção de múltiplos números
        System.out.print("Quantos números deseja inserir? ");
        int count = scanner.nextInt();

        System.out.println("Digite os números:");
        for (int i = 0; i < count; i++) {
            int num = scanner.nextInt();
            tree.insert(num);
        }

        // Exibição dos percursos
        System.out.println("\nPercurso em ordem:");
        tree.inorder();

        System.out.println("Percurso pré-ordem:");
        tree.preorder();

        System.out.println("Percurso pós-ordem:");
        tree.postorder();

        // Exibição da altura da árvore
        System.out.println("Altura da árvore: " + tree.height());

        // Exibição do nível de um nó
        System.out.print("Informe um valor para verificar o nível: ");
        int levelQuery = scanner.nextInt();
        System.out.println("Nível do nó " + levelQuery + ": " + tree.getLevel(levelQuery));

        // Exibição do grau de um nó
        System.out.print("Informe um valor para verificar o grau: ");
        int degreeQuery = scanner.nextInt();
        System.out.println("Grau do nó " + degreeQuery + ": " + tree.getDegree(degreeQuery));

        // Busca de um valor
        System.out.print("Informe um valor para buscar: ");
        int search = scanner.nextInt();
        System.out.println("Busca por " + search + ": " + tree.search(search));

        // Verificação das propriedades da árvore
        System.out.println("A árvore é estritamente binária? " + tree.isStrictlyBinary(tree.root));
        System.out.println("A árvore é completa? " + tree.isComplete());
        System.out.println("A árvore é cheia? " + tree.isFull(tree.root));

        // Remoção de múltiplos nós
        boolean removeAnother = true;
        while (removeAnother) {
            System.out.print("Informe um valor para remover: ");
            int remove = scanner.nextInt();
            tree.remove(remove);
            System.out.println("Percurso em ordem após remoção:");
            tree.inorder();

            // Pergunta se o usuário deseja remover outro nó
            System.out.print("Deseja remover outro nó? (sim/nao): ");
            String response = scanner.next();
            removeAnother = response.equalsIgnoreCase("sim");
        }

        scanner.close(); // Fechar o scanner após o uso
    }
}
