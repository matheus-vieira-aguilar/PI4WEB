package GerenciadorDeAcademiWeb.Enums;


public enum SexoEnum{
    MASCULINO(1),
    FEMININO(2);

    public int valorSexo;

    SexoEnum(int valor) {
        valorSexo = valor;
    }

    SexoEnum(String valor){
        valorSexo = Integer.parseInt(valor);
    }
    public int getInt(){
        return valorSexo;
    }
    

}