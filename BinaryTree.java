// Classe que representa um nó da árvore binária
class Node {
    int data; // valor armazenado no nó
    Node left, right; // ponteiros para os filhos esquerdo e direito

    // Construtor: inicializa o nó com o valor recebido
    public Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    // Verifica se o nó é uma folha (não possui filhos)
    public boolean isLeaf() {
        return left == null && right == null;
    }
}

// Classe principal da árvore binária
public class BinaryTree {
    private Node root; // raiz da árvore

    // Insere um valor na árvore
    public void insert(int value) {
        root = insertRec(root, value);
    }

    // Função recursiva para inserir um valor respeitando a ordenação binária
    private Node insertRec(Node root, int value) {
        if (root == null) return new Node(value);
        if (value < root.data) root.left = insertRec(root.left, value);
        else root.right = insertRec(root.right, value);
        return root;
    }

    // Remove um valor da árvore
    public void remove(int value) {
        root = removeRec(root, value);
    }

    // Função recursiva para remover um nó
    private Node removeRec(Node root, int value) {
        if (root == null) return null;
        if (value < root.data) root.left = removeRec(root.left, value);
        else if (value > root.data) root.right = removeRec(root.right, value);
        else {
            // Caso 1: nó com um ou nenhum filho
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;
            // Caso 2: nó com dois filhos - substitui pelo sucessor
            root.data = minValue(root.right);
            root.right = removeRec(root.right, root.data);
        }
        return root;
    }

    // Retorna o menor valor da subárvore (usado na remoção)
    private int minValue(Node root) {
        int min = root.data;
        while (root.left != null) {
            min = root.left.data;
            root = root.left;
        }
        return min;
    }

    // Impressão em ordem (esquerda, raiz, direita)
    public void inorder() {
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.data + " ");
            inorderRec(root.right);
        }
    }

    // Impressão pré-ordem (raiz, esquerda, direita)
    public void preorder() {
        preorderRec(root);
        System.out.println();
    }

    private void preorderRec(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preorderRec(root.left);
            preorderRec(root.right);
        }
    }

    // Impressão pós-ordem (esquerda, direita, raiz)
    public void postorder() {
        postorderRec(root);
        System.out.println();
    }

    private void postorderRec(Node root) {
        if (root != null) {
            postorderRec(root.left);
            postorderRec(root.right);
            System.out.print(root.data + " ");
        }
    }

    // Calcula a altura da árvore (profundidade máxima)
    public int getHeight() {
        return getHeightRec(root);
    }

    private int getHeightRec(Node node) {
        if (node == null) return -1;
        return 1 + Math.max(getHeightRec(node.left), getHeightRec(node.right));
    }

    // Retorna o nível (profundidade) de um nó com valor específico
    public int getNodeLevel(int value) {
        return getNodeLevelRec(root, value, 0);
    }

    private int getNodeLevelRec(Node node, int value, int level) {
        if (node == null) return -1;
        if (node.data == value) return level;
        int downlevel = getNodeLevelRec(node.left, value, level + 1);
        if (downlevel != -1) return downlevel;
        return getNodeLevelRec(node.right, value, level + 1);
    }

    // Retorna o grau de um nó (quantidade de filhos)
    public int getDegree(int value) {
        Node node = searchNode(root, value);
        if (node == null) return -1;
        int degree = 0;
        if (node.left != null) degree++;
        if (node.right != null) degree++;
        return degree;
    }

    // Busca um nó com o valor especificado
    private Node searchNode(Node root, int value) {
        if (root == null || root.data == value) return root;
        if (value < root.data) return searchNode(root.left, value);
        return searchNode(root.right, value);
    }

    // Verifica se a árvore é estritamente binária
    public boolean isStrictlyBinary() {
        return isStrictlyBinaryRec(root);
    }

    private boolean isStrictlyBinaryRec(Node node) {
        if (node == null) return true;
        if ((node.left == null && node.right != null) || (node.left != null && node.right == null))
            return false;
        return isStrictlyBinaryRec(node.left) && isStrictlyBinaryRec(node.right);
    }

    // Verifica se a árvore é completa
    public boolean isComplete() {
        int totalNodes = countNodes(root);
        return isCompleteRec(root, 0, totalNodes);
    }

    private boolean isCompleteRec(Node root, int index, int totalNodes) {
        if (root == null) return true;
        if (index >= totalNodes) return false;
        return isCompleteRec(root.left, 2 * index + 1, totalNodes) &&
               isCompleteRec(root.right, 2 * index + 2, totalNodes);
    }

    // Conta o número total de nós da árvore
    private int countNodes(Node node) {
        if (node == null) return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    // Verifica se a árvore é cheia (todos os nós têm 0 ou 2 filhos e a árvore é completa)
    public boolean isFull() {
        return isFullRec(root);
    }

    private boolean isFullRec(Node node) {
        if (node == null) return true;
        if ((node.left == null && node.right == null)) return true;
        if (node.left != null && node.right != null)
            return isFullRec(node.left) && isFullRec(node.right);
        return false;
    }

    // Verifica se um valor existe na árvore
    public boolean search(int value) {
        return searchNode(root, value) != null;
    }

    // Função principal com exemplos de uso da árvore
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        // Inserção de elementos
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.insert(80);

        // Impressões dos percursos
        System.out.println("Percurso em ordem:");
        tree.inorder();

        System.out.println("Percurso pré-ordem:");
        tree.preorder();

        System.out.println("Percurso pós-ordem:");
        tree.postorder();

        // Verificações
        System.out.println("Altura da árvore: " + tree.getHeight());
        System.out.println("Nível do nó 60: " + tree.getNodeLevel(60));
        System.out.println("Grau do nó 30: " + tree.getDegree(30));

        System.out.println("Busca por 40: " + tree.search(40));
        System.out.println("A árvore é estritamente binária? " + tree.isStrictlyBinary());
        System.out.println("A árvore é completa? " + tree.isComplete());
        System.out.println("A árvore é cheia? " + tree.isFull());

        // Remoção de elementos e nova impressão
        System.out.println("Removendo 20 e 30...");
        tree.remove(20);
        tree.remove(30);
        tree.inorder();
    }
}
    