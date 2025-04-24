import java.util.Random;  // Importa a classe Random para gerar números aleatórios

// Classe que representa um nó da árvore
class NodeRandom {
    int data;  // Valor armazenado no nó
    NodeRandom left, right;  // Referências para os filhos esquerdo e direito

    // Construtor que inicializa o nó com um valor
    public NodeRandom(int data) {
        this.data = data;
        this.left = null;  // Filho esquerdo inicializado como nulo
        this.right = null;  // Filho direito inicializado como nulo
    }
}

// Classe principal da árvore binária
public class BinaryTreeRandom {
    private NodeRandom root;  // Raiz da árvore

    // Método para inserir um valor na árvore
    public void insert(int value) {
        root = insertRec(root, value);  // Chama o método recursivo para inserir o valor
    }

    // Método recursivo para inserir um valor na árvore
    private NodeRandom insertRec(NodeRandom root, int value) {
        if (root == null) return new NodeRandom(value);  // Se a raiz for nula, cria um novo nó
        if (value < root.data) root.left = insertRec(root.left, value);  // Se o valor for menor, insere à esquerda
        else root.right = insertRec(root.right, value);  // Se o valor for maior, insere à direita
        return root;  // Retorna a raiz após a inserção
    }

    // Método para realizar o percurso em ordem (in-order)
    public void inorder() {
        inorderRec(root);  // Chama o método recursivo para o percurso em ordem
        System.out.println();  // Nova linha após o percurso
    }

    // Método recursivo para percurso em ordem (in-order)
    private void inorderRec(NodeRandom root) {
        if (root != null) {  // Se o nó não for nulo
            inorderRec(root.left);  // Percorre a subárvore esquerda
            System.out.print(root.data + " ");  // Imprime o valor do nó
            inorderRec(root.right);  // Percorre a subárvore direita
        }
    }

    // Método para realizar o percurso pré-ordem (pre-order)
    public void preorder() {
        preorderRec(root);  // Chama o método recursivo para o percurso pré-ordem
        System.out.println();  // Nova linha após o percurso
    }

    // Método recursivo para percurso pré-ordem (pre-order)
    private void preorderRec(NodeRandom root) {
        if (root != null) {  // Se o nó não for nulo
            System.out.print(root.data + " ");  // Imprime o valor do nó
            preorderRec(root.left);  // Percorre a subárvore esquerda
            preorderRec(root.right);  // Percorre a subárvore direita
        }
    }

    // Método para realizar o percurso pós-ordem (post-order)
    public void postorder() {
        postorderRec(root);  // Chama o método recursivo para o percurso pós-ordem
        System.out.println();  // Nova linha após o percurso
    }

    // Método recursivo para percurso pós-ordem (post-order)
    private void postorderRec(NodeRandom root) {
        if (root != null) {  // Se o nó não for nulo
            postorderRec(root.left);  // Percorre a subárvore esquerda
            postorderRec(root.right);  // Percorre a subárvore direita
            System.out.print(root.data + " ");  // Imprime o valor do nó
        }
    }

    // Método para calcular a altura da árvore
    public int height() {
        return heightRec(root);  // Chama o método recursivo para calcular a altura
    }

    // Método recursivo para calcular a altura da árvore
    private int heightRec(NodeRandom root) {
        if (root == null) return 0;  // Se o nó for nulo, a altura é 0
        // Retorna 1 (para o nó atual) mais o máximo entre a altura da subárvore esquerda e direita
        return 1 + Math.max(heightRec(root.left), heightRec(root.right));
    }

    // Método para calcular o nível de um nó na árvore
    public int getLevel(NodeRandom root, int key, int level) {
        if (root == null) return 0;  // Se o nó for nulo, retorna 0
        if (root.data == key) return level;  // Se encontrou o nó, retorna o nível
        // Chama recursivamente para a subárvore esquerda e direita, incrementando o nível
        int res = getLevel(root.left, key, level + 1);
        if (res != 0) return res;  // Se encontrou na esquerda, retorna o nível
        return getLevel(root.right, key, level + 1);  // Caso contrário, verifica a subárvore direita
    }

    // Método para calcular o grau de um nó (número de filhos)
    public int degree(NodeRandom node) {
        int degree = 0;  // Inicializa o grau como 0
        if (node.left != null) degree++;  // Se o nó tiver filho esquerdo, incrementa o grau
        if (node.right != null) degree++;  // Se o nó tiver filho direito, incrementa o grau
        return degree;  // Retorna o grau do nó
    }

    // Método para buscar um valor na árvore
    public boolean search(int key) {
        return searchRec(root, key);  // Chama o método recursivo para realizar a busca
    }

    // Método recursivo para buscar um valor na árvore
    private boolean searchRec(NodeRandom root, int key) {
        if (root == null) return false;  // Se o nó for nulo, retorna falso
        if (root.data == key) return true;  // Se encontrou o valor, retorna verdadeiro
        // Busca recursivamente nas subárvores esquerda e direita
        return searchRec(root.left, key) || searchRec(root.right, key);
    }

    // Método para verificar se a árvore é estritamente binária
    public boolean isStrictlyBinary() {
        return isStrictlyBinaryRec(root);  // Chama o método recursivo para verificar
    }

    // Método recursivo para verificar se a árvore é estritamente binária
    private boolean isStrictlyBinaryRec(NodeRandom root) {
        if (root == null) return true;  // Se o nó for nulo, a árvore é estritamente binária
        // Verifica se o nó tem exatamente dois filhos ou nenhum
        if ((root.left == null && root.right != null) || (root.left != null && root.right == null))
            return false;  // Se tiver um filho apenas, não é estritamente binária
        return isStrictlyBinaryRec(root.left) && isStrictlyBinaryRec(root.right);  // Verifica recursivamente
    }

    // Método para verificar se a árvore é completa
    public boolean isComplete() {
        int nodeCount = countNodes(root);  // Conta o número total de nós na árvore
        return isCompleteRec(root, 0, nodeCount);  // Chama o método recursivo para verificar a completude
    }

    // Método recursivo para verificar se a árvore é completa
    private boolean isCompleteRec(NodeRandom root, int index, int nodeCount) {
        if (root == null) return true;  // Se o nó for nulo, retorna verdadeiro
        if (index >= nodeCount) return false;  // Se o índice for maior ou igual ao número de nós, não é completa
        // Verifica recursivamente nas subárvores esquerda e direita, com os índices correspondentes
        return isCompleteRec(root.left, 2 * index + 1, nodeCount) &&
               isCompleteRec(root.right, 2 * index + 2, nodeCount);
    }

    // Método para contar o número total de nós na árvore
    private int countNodes(NodeRandom root) {
        if (root == null) return 0;  // Se o nó for nulo, retorna 0
        return 1 + countNodes(root.left) + countNodes(root.right);  // Conta o nó atual e as subárvores
    }

    // Método para verificar se a árvore é cheia
    public boolean isFull() {
        return isFullRec(root);  // Chama o método recursivo para verificar se a árvore é cheia
    }

    // Método recursivo para verificar se a árvore é cheia
    private boolean isFullRec(NodeRandom root) {
        if (root == null) return true;  // Se o nó for nulo, a árvore é cheia
        // Verifica se o nó tem exatamente dois filhos ou nenhum
        if ((root.left == null && root.right != null) || (root.left != null && root.right == null))
            return false;  // Se tiver um filho apenas, não é cheia
        return isFullRec(root.left) && isFullRec(root.right);  // Verifica recursivamente
    }

    // Método para remover todos os nós da árvore
    public void removeAll() {
        root = null;  // Atribui null à raiz, efetivamente removendo todos os nós
    }

    // Método principal para testar a árvore
    public static void main(String[] args) {
        BinaryTreeRandom tree = new BinaryTreeRandom();  // Cria uma nova árvore binária
        Random rand = new Random();  // Cria uma instância de Random para gerar números aleatórios

        System.out.println("Inserindo 10 números aleatórios na árvore:");
        int[] nums = new int[10];  // Array para armazenar os números gerados
        for (int i = 0; i < 10; i++) {
            nums[i] = rand.nextInt(100);  // Gera um número aleatório entre 0 e 99
            System.out.print(nums[i] + " ");  // Imprime o número gerado
            tree.insert(nums[i]);  // Insere o número na árvore
        }

        System.out.println("\n\nPercurso em ordem:");
        tree.inorder();  // Exibe o percurso em ordem

        System.out.println("Percurso pré-ordem:");
        tree.preorder();  // Exibe o percurso pré-ordem

        System.out.println("Percurso pós-ordem:");
        tree.postorder();  // Exibe o percurso pós-ordem

        System.out.println("Altura da árvore: " + tree.height());  // Exibe a altura da árvore

        // Exemplo de Nível de um nó (usando o primeiro número)
        int levelValue = nums[0];  
        System.out.println("Nível do nó com valor " + levelValue + ": " + tree.getLevel(tree.root, levelValue, 1));

        // Exemplo de Grau de um nó (usando o segundo número)
        int degreeValue = nums[1];
        System.out.println("Grau do nó com valor " + degreeValue + ": " + tree.degree(tree.root));

        // Busca por um valor (usando o terceiro número)
        int searchValue = nums[2];
        System.out.println("Busca pelo valor " + searchValue + ": " + tree.search(searchValue));

        // Verificações de propriedades
        System.out.println("A árvore é estritamente binária? " + tree.isStrictlyBinary());
        System.out.println("A árvore é completa? " + tree.isComplete());
        System.out.println("A árvore é cheia? " + tree.isFull());

        // Removendo todos os nós
        tree.removeAll();  
        System.out.println("Árvore limpa. Percurso em ordem após remoção:");
        tree.inorder();  // Exibe o percurso após a remoção
    }
}
