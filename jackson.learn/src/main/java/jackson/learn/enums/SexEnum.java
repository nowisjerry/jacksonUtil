package jackson.learn.enums;

/**
 * �Ա�ö��
 */
public enum SexEnum {
    MAN("man", "��"),
    WOMAN("woman","Ů");

    private String code;
    private String name;
    private SexEnum(String code, String name) {
        this.code=code;
        this.name=name;
    }

    public String getCode() {
        return code;
    }
    
    public String getName() {
        return this.name;
    }

    public static boolean contains(String code){
        for (SexEnum _enum : values()) {
            if(_enum.getCode().equals(code)){
                return true;
            }
        }
        return false;
    };
    
    @Override  
    public String toString() {  
        return code + " " + name;  
    }  
    
}
