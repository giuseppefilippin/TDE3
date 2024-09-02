public class Main {
    public static void main(String[] args) {
        ListaEncadeada lista = new ListaEncadeada();

        lista.inserePrimeiro(10);
        lista.inserePrimeiro(5);
        lista.insereUltimo(15);
        lista.insereOrdenado(7);

        System.out.println("Elementos da lista:");
        lista.imprime();

        Node node = lista.getNode(10);
        lista.insereDepois(node, 12);

        System.out.println("Elementos da lista após inserir 12 depois de 10:");
        lista.imprime();

        System.out.println("Removendo o primeiro elemento: " + lista.removePrimeiro().getInformacao());
        lista.imprime();

        System.out.println("Removendo o último elemento: " + lista.removeUltimo().getInformacao());
        lista.imprime();

        Node toRemove = lista.getNode(7);
        if (toRemove != null) {
            System.out.println("Removendo o nó com valor 7: " + lista.remove(toRemove).getInformacao());
        }
        lista.imprime();
    }
}

class Node {
    private Integer informacao;
    private Node proximo;

    public Node() {
        informacao = null;
        proximo = null;
    }
    public Node(Integer info) {
        this.informacao = info;
        this.proximo = null;
    }
    public Integer getInformacao() {
        return informacao;
    }
    public void setInformacao(Integer informacao) {
        this.informacao = informacao;
    }
    public Node getProximo() {
        return proximo;
    }
    public void setProximo(Node proximo) {
        this.proximo = proximo;
    }
}

class ListaEncadeada {
    private Node lista;

    public ListaEncadeada() {
        lista = null;
    }

    public boolean vazia() {
        return lista == null;
    }

    public void inserePrimeiro(int info) {
        Node novoNode = new Node(info);
        novoNode.setProximo(this.lista);
        lista = novoNode;
    }

    public void insereDepois(Node node, int info) {
        if (node != null) {
            Node novoNode = new Node(info);
            novoNode.setProximo(node.getProximo());
            node.setProximo(novoNode);
        }
    }

    public void insereUltimo(int info) {
        Node novoNode = new Node(info);
        if (vazia()) {
            lista = novoNode;
        } else {
            Node temp = lista;
            while (temp.getProximo() != null) {
                temp = temp.getProximo();
            }
            temp.setProximo(novoNode);
        }
    }

    public void insereOrdenado(int info) {
        Node novoNode = new Node(info);
        if (vazia() || lista.getInformacao() > info) {
            novoNode.setProximo(lista);
            lista = novoNode;
        } else {
            Node temp = lista;
            while (temp.getProximo() != null && temp.getProximo().getInformacao() < info) {
                temp = temp.getProximo();
            }
            novoNode.setProximo(temp.getProximo());
            temp.setProximo(novoNode);
        }
    }

    public Node getNode(int info) {
        Node temp = lista;
        while (temp != null) {
            if (temp.getInformacao().equals(info)) {
                return temp;
            }
            temp = temp.getProximo();
        }
        return null;
    }

    public void imprime() {
        Node temp = lista;
        while (temp != null) {
            System.out.print(temp.getInformacao() + " ");
            temp = temp.getProximo();
        }
        System.out.println();
    }

    public Node removePrimeiro() {
        if (vazia()) {
            return null;
        }
        Node temp = lista;
        lista = lista.getProximo();
        return temp;
    }

    public Node removeUltimo() {
        if (vazia()) {
            return null;
        }
        if (lista.getProximo() == null) {
            Node temp = lista;
            lista = null;
            return temp;
        }
        Node temp = lista;
        while (temp.getProximo().getProximo() != null) {
            temp = temp.getProximo();
        }
        Node ultimo = temp.getProximo();
        temp.setProximo(null);
        return ultimo;
    }

    public Node remove(Node no) {
        if (vazia() || no == null) {
            return null;
        }
        if (lista == no) {
            lista = lista.getProximo();
            return no;
        }
        Node temp = lista;
        while (temp.getProximo() != null && temp.getProximo() != no) {
            temp = temp.getProximo();
        }
        if (temp.getProximo() == no) {
            temp.setProximo(no.getProximo());
            return no;
        }
        return null;
    }
}
