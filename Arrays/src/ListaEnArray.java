public class ListaEnArray implements Lista {
    private int size;
    private Object[] v;

    public ListaEnArray(){
        size = 0;
        v = new Object[10];
    }

    public void add(Object nro){
        if(size == v.length){
            enlargeCapacity();
        }
        v[size] = nro;
        size++;
    }

    public void enlargeCapacity(){
        int newSize = (int)( v.length * 1.5);
        Object[] aux = new Object[newSize];
        System.arraycopy(v, 0,aux,0, v.length); // más rápido que un for
        v = aux;
    }
    public int size(){
        return size;
    }

    public Object get(int i){
        return v[i];
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(v[i]);
            sb.append(",");

        }
        sb.append("]");
        return sb.toString();
    }
}
