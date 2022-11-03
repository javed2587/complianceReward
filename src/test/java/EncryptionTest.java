import com.ssa.cms.util.EncryptionHandlerUtil;


public class EncryptionTest {

   // @Test
    public static void main(String[] ArSt) {

        String encrypted = "/QXnJR3ybDcEDvLfJSGfHjRD6r1sMfeaNy+cG365oP8=";
        String decrypted = "rama";
        //ipe59pEEzjqI0G9Mttu8+w==
        System.out.println(EncryptionHandlerUtil.getDecryptedString(encrypted));
        System.out.println(EncryptionHandlerUtil.getEncryptedString(decrypted));

//      String str = "dj\ndj\ndj";
//      System.out.println(str);
//      System.out.println(str);
//      System.out.println(DateUtil.changeDateFormat("2019-01-01 11:34:07.0", "yyyy-MM-dd mm:HH:ss", "dd"));
    }

}

