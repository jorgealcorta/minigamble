package minigamble;

public class Hash {

	
	
    /**
     * Método que de un String devuelve otro String encriptado mediante el algoritmo MD5
     * @param txt Strig que se quiere encriptar
     * @return String encriptado
     */
    public static String md5(String txt) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest
                    .getInstance("MD5");
            byte[] array = md.digest(txt.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
                        .substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return null;
        
        
        
    }

}
