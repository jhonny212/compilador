// DO NOT EDIT
// Generated by JFlex 1.8.2 http://jflex.de/
// source: src/main/java/GramaticaJAVA/javalex.jflex

package GramaticaJAVA;
import java_cup.runtime.Symbol;
import Errores.ErrorClass;

// See https://github.com/jflex-de/jflex/issues/222
@SuppressWarnings("FallThrough")
public class javalex implements java_cup.runtime.Scanner {

  /** This character denotes the end of file. */
  public static final int YYEOF = -1;

  /** Initial size of the lookahead buffer. */
  private static final int ZZ_BUFFERSIZE = 16384;

  // Lexical states.
  public static final int YYINITIAL = 0;
  public static final int VERIFY = 2;
  public static final int STRING = 4;
  public static final int CHAR = 6;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = {
     0,  0,  1,  1,  2,  2,  3, 3
  };

  /**
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = {
     0,  0,  0,  0,  0,  0,  0,  0,  0,  1,  2,  3,  3,  3,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,  4,  4,  4, 
     5,  6,  7,  4,  8,  9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 
    20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 21, 22, 23, 24, 23,  4, 
     4,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8, 
    25,  8,  8, 26,  8,  8,  8,  8,  8, 27,  8,  4, 28,  4,  4,  8, 
     4, 29, 30, 31, 32, 33, 34,  8, 35, 36,  8, 37, 38, 39, 40, 41, 
    42,  8, 43, 44, 45, 46, 47, 48,  8, 49,  8, 50, 51, 52,  4,  0, 
     0,  0,  0,  0,  0, 53,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     4,  4,  8,  8,  8,  8,  4,  4,  4,  4,  8,  4,  4,  0,  4,  4, 
     4,  4,  4,  4,  4,  8,  4,  4,  4,  4,  8,  4,  4,  4,  4,  4, 
     8,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8, 
     8,  8,  8,  8,  8,  8,  8,  4,  8,  8,  8,  8,  8,  8,  8,  8, 
     8,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8, 
     8,  8,  8,  8,  8,  8,  8,  4,  8,  8,  8,  8,  8,  8,  8,  8
  };

  /**
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\4\0\1\1\3\2\1\3\1\4\1\5\1\6\1\1"+
    "\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16"+
    "\1\17\1\20\1\21\1\22\1\23\15\5\1\24\1\1"+
    "\1\25\1\26\1\27\1\26\1\30\1\31\1\30\1\32"+
    "\1\33\1\22\1\0\1\34\1\0\6\5\1\35\3\5"+
    "\1\36\7\5\1\37\1\40\1\41\1\42\1\43\1\44"+
    "\1\0\1\45\11\5\1\46\1\47\6\5\1\50\2\5"+
    "\1\51\1\52\3\5\1\53\6\5\1\54\2\5\1\55"+
    "\1\5\1\56\2\5\1\57\4\5\1\0\1\60\3\5"+
    "\1\57\2\5\1\61\1\62\1\63\1\0\1\5\1\64"+
    "\2\5\1\0\2\5\1\65\1\0\1\66\1\5\1\0"+
    "\1\67\5\0\1\70\1\0\1\71";

  private static int [] zzUnpackAction() {
    int [] result = new int[158];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\66\0\154\0\242\0\330\0\u010e\0\u0144\0\u017a"+
    "\0\u01b0\0\330\0\u01e6\0\u021c\0\u0252\0\330\0\330\0\330"+
    "\0\330\0\330\0\330\0\u0288\0\330\0\u02be\0\330\0\330"+
    "\0\u01b0\0\330\0\u02f4\0\u032a\0\u0360\0\u0396\0\u03cc\0\u0402"+
    "\0\u0438\0\u046e\0\u04a4\0\u04da\0\u0510\0\u0546\0\u057c\0\330"+
    "\0\u05b2\0\330\0\u05e8\0\330\0\u061e\0\330\0\330\0\u0654"+
    "\0\330\0\330\0\330\0\u068a\0\330\0\u06c0\0\u06f6\0\u072c"+
    "\0\u0762\0\u0798\0\u07ce\0\u0804\0\u083a\0\u0870\0\u08a6\0\u08dc"+
    "\0\u01e6\0\u0912\0\u0948\0\u097e\0\u09b4\0\u09ea\0\u0a20\0\u0a56"+
    "\0\330\0\330\0\330\0\330\0\330\0\330\0\u0a8c\0\330"+
    "\0\u0ac2\0\u0af8\0\u0b2e\0\u0b64\0\u0b9a\0\u0bd0\0\u0c06\0\u0c3c"+
    "\0\u0c72\0\u01e6\0\u0ca8\0\u0cde\0\u0d14\0\u0d4a\0\u0d80\0\u0db6"+
    "\0\u0dec\0\330\0\u0e22\0\u0e58\0\u01e6\0\u0e8e\0\u0ec4\0\u0efa"+
    "\0\u0f30\0\u01e6\0\u0f66\0\u0f9c\0\u0fd2\0\u1008\0\u103e\0\u1074"+
    "\0\u01e6\0\u10aa\0\u10e0\0\u01e6\0\u1116\0\u01e6\0\u114c\0\u1182"+
    "\0\u11b8\0\u11ee\0\u1224\0\u125a\0\u1290\0\u12c6\0\u01e6\0\u12fc"+
    "\0\u1332\0\u1368\0\u01e6\0\u139e\0\u13d4\0\u01e6\0\u01e6\0\u01e6"+
    "\0\u140a\0\u1440\0\u01e6\0\u1476\0\u14ac\0\u14e2\0\u1518\0\u154e"+
    "\0\u01e6\0\u1584\0\u01e6\0\u15ba\0\u15f0\0\u01e6\0\u1626\0\u165c"+
    "\0\u1692\0\u16c8\0\u16fe\0\u1734\0\u176a\0\330";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[158];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /**
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\5\1\6\1\7\1\0\1\5\1\10\1\11\1\12"+
    "\1\13\1\14\1\15\1\16\1\17\1\20\1\21\1\22"+
    "\1\23\1\24\1\5\1\25\1\26\1\27\1\30\1\31"+
    "\1\32\1\13\1\33\1\13\1\5\1\13\1\34\1\35"+
    "\1\36\1\37\1\40\1\13\1\41\5\13\1\42\1\43"+
    "\1\44\1\45\1\13\1\46\1\47\1\13\1\50\1\51"+
    "\1\52\1\0\7\53\1\54\24\53\1\55\31\53\13\56"+
    "\1\57\20\56\1\60\31\56\2\61\2\0\7\61\1\62"+
    "\51\61\70\0\1\6\66\0\1\7\70\0\1\10\110\0"+
    "\1\63\35\0\1\13\7\0\1\13\13\0\1\13\4\0"+
    "\3\13\1\0\25\13\3\0\1\13\11\0\1\64\66\0"+
    "\1\65\77\0\1\26\63\0\1\66\1\0\1\26\41\0"+
    "\1\13\7\0\1\13\13\0\1\13\4\0\3\13\1\0"+
    "\24\13\1\67\3\0\2\13\7\0\1\13\13\0\1\13"+
    "\4\0\3\13\1\0\16\13\1\70\6\13\3\0\2\13"+
    "\7\0\1\13\13\0\1\13\4\0\3\13\1\0\1\71"+
    "\5\13\1\72\2\13\1\73\13\13\3\0\2\13\7\0"+
    "\1\13\13\0\1\13\4\0\3\13\1\0\4\13\1\74"+
    "\7\13\1\75\10\13\3\0\2\13\7\0\1\13\13\0"+
    "\1\13\4\0\3\13\1\0\11\13\1\76\13\13\3\0"+
    "\2\13\7\0\1\13\13\0\1\13\4\0\3\13\1\0"+
    "\11\13\1\77\2\13\1\100\10\13\3\0\2\13\7\0"+
    "\1\13\13\0\1\13\4\0\3\13\1\0\5\13\1\101"+
    "\5\13\1\102\11\13\3\0\2\13\7\0\1\13\13\0"+
    "\1\13\4\0\3\13\1\0\21\13\1\103\3\13\3\0"+
    "\2\13\7\0\1\13\13\0\1\13\4\0\3\13\1\0"+
    "\4\13\1\104\20\13\3\0\2\13\7\0\1\13\13\0"+
    "\1\13\4\0\3\13\1\0\23\13\1\105\1\13\3\0"+
    "\2\13\7\0\1\13\13\0\1\13\4\0\3\13\1\0"+
    "\6\13\1\106\16\13\3\0\2\13\7\0\1\13\13\0"+
    "\1\13\4\0\3\13\1\0\14\13\1\107\10\13\3\0"+
    "\2\13\7\0\1\13\13\0\1\13\4\0\3\13\1\0"+
    "\6\13\1\110\16\13\3\0\1\13\63\0\1\111\2\0"+
    "\7\53\1\0\65\53\1\112\56\53\7\0\1\113\40\0"+
    "\1\114\2\0\1\115\1\0\1\116\41\0\1\117\60\0"+
    "\1\120\41\0\1\13\7\0\1\13\13\0\1\13\4\0"+
    "\3\13\1\0\17\13\1\121\5\13\3\0\2\13\7\0"+
    "\1\13\13\0\1\13\4\0\3\13\1\0\4\13\1\122"+
    "\20\13\3\0\2\13\7\0\1\13\13\0\1\13\4\0"+
    "\3\13\1\0\17\13\1\123\5\13\3\0\2\13\7\0"+
    "\1\13\13\0\1\13\4\0\3\13\1\0\1\124\24\13"+
    "\3\0\2\13\7\0\1\13\13\0\1\13\4\0\3\13"+
    "\1\0\1\125\24\13\3\0\2\13\7\0\1\13\13\0"+
    "\1\13\4\0\3\13\1\0\5\13\1\126\17\13\3\0"+
    "\2\13\7\0\1\13\13\0\1\13\4\0\3\13\1\0"+
    "\21\13\1\127\3\13\3\0\2\13\7\0\1\13\13\0"+
    "\1\13\4\0\3\13\1\0\17\13\1\130\5\13\3\0"+
    "\2\13\7\0\1\13\13\0\1\13\4\0\3\13\1\0"+
    "\14\13\1\131\10\13\3\0\2\13\7\0\1\13\13\0"+
    "\1\13\4\0\3\13\1\0\16\13\1\132\6\13\3\0"+
    "\2\13\7\0\1\13\13\0\1\13\4\0\3\13\1\0"+
    "\20\13\1\133\4\13\3\0\2\13\7\0\1\13\13\0"+
    "\1\13\4\0\3\13\1\0\1\13\1\134\23\13\3\0"+
    "\2\13\7\0\1\13\13\0\1\13\4\0\3\13\1\0"+
    "\20\13\1\135\4\13\3\0\2\13\7\0\1\13\13\0"+
    "\1\13\4\0\3\13\1\0\7\13\1\136\15\13\3\0"+
    "\2\13\7\0\1\13\13\0\1\13\4\0\3\13\1\0"+
    "\7\13\1\137\15\13\3\0\2\13\7\0\1\13\13\0"+
    "\1\13\4\0\3\13\1\0\7\13\1\140\15\13\3\0"+
    "\2\13\7\0\1\13\13\0\1\13\4\0\3\13\1\0"+
    "\7\13\1\141\15\13\3\0\1\13\33\0\1\142\32\0"+
    "\1\13\7\0\1\13\13\0\1\13\4\0\3\13\1\0"+
    "\20\13\1\143\4\13\3\0\2\13\7\0\1\13\13\0"+
    "\1\13\4\0\3\13\1\0\1\144\24\13\3\0\2\13"+
    "\7\0\1\13\13\0\1\13\4\0\3\13\1\0\4\13"+
    "\1\145\20\13\3\0\2\13\7\0\1\13\13\0\1\13"+
    "\4\0\3\13\1\0\16\13\1\146\6\13\3\0\2\13"+
    "\7\0\1\13\13\0\1\13\4\0\3\13\1\0\17\13"+
    "\1\147\5\13\3\0\2\13\7\0\1\13\13\0\1\13"+
    "\4\0\3\13\1\0\1\150\24\13\3\0\2\13\7\0"+
    "\1\13\13\0\1\13\4\0\3\13\1\0\1\13\1\151"+
    "\23\13\3\0\2\13\7\0\1\13\13\0\1\13\4\0"+
    "\3\13\1\0\4\13\1\152\20\13\3\0\2\13\7\0"+
    "\1\13\13\0\1\13\4\0\3\13\1\0\1\153\24\13"+
    "\3\0\2\13\7\0\1\13\13\0\1\13\4\0\3\13"+
    "\1\0\7\13\1\154\15\13\3\0\2\13\7\0\1\13"+
    "\13\0\1\13\4\0\3\13\1\0\11\13\1\155\13\13"+
    "\3\0\2\13\7\0\1\13\13\0\1\13\4\0\3\13"+
    "\1\0\21\13\1\156\3\13\3\0\2\13\7\0\1\13"+
    "\13\0\1\13\4\0\3\13\1\0\20\13\1\157\4\13"+
    "\3\0\2\13\7\0\1\13\13\0\1\13\4\0\3\13"+
    "\1\0\17\13\1\160\5\13\3\0\2\13\7\0\1\13"+
    "\13\0\1\13\4\0\3\13\1\0\3\13\1\161\21\13"+
    "\3\0\2\13\7\0\1\13\13\0\1\13\4\0\3\13"+
    "\1\0\11\13\1\162\13\13\3\0\2\13\7\0\1\13"+
    "\13\0\1\13\4\0\3\13\1\0\4\13\1\163\20\13"+
    "\3\0\2\13\7\0\1\13\13\0\1\13\4\0\3\13"+
    "\1\0\10\13\1\164\14\13\3\0\2\13\7\0\1\13"+
    "\13\0\1\13\4\0\3\13\1\0\7\13\1\165\15\13"+
    "\3\0\2\13\7\0\1\13\13\0\1\13\4\0\3\13"+
    "\1\0\17\13\1\166\5\13\3\0\2\13\7\0\1\13"+
    "\13\0\1\13\4\0\3\13\1\0\21\13\1\167\3\13"+
    "\3\0\2\13\7\0\1\13\13\0\1\13\4\0\3\13"+
    "\1\0\11\13\1\170\13\13\3\0\2\13\7\0\1\13"+
    "\13\0\1\13\4\0\3\13\1\0\20\13\1\171\4\13"+
    "\3\0\2\13\7\0\1\13\13\0\1\13\4\0\3\13"+
    "\1\0\13\13\1\172\11\13\3\0\2\13\7\0\1\13"+
    "\13\0\1\13\4\0\3\13\1\0\7\13\1\173\15\13"+
    "\3\0\2\13\7\0\1\13\13\0\1\13\4\0\3\13"+
    "\1\0\16\13\1\174\6\13\3\0\2\13\7\0\1\13"+
    "\13\0\1\13\4\0\3\13\1\0\2\13\1\175\22\13"+
    "\3\0\2\13\7\0\1\13\11\0\1\176\1\0\1\13"+
    "\4\0\3\13\1\0\25\13\3\0\2\13\7\0\1\13"+
    "\13\0\1\13\4\0\3\13\1\0\4\13\1\177\20\13"+
    "\3\0\2\13\7\0\1\13\13\0\1\13\4\0\3\13"+
    "\1\0\12\13\1\200\12\13\3\0\2\13\7\0\1\13"+
    "\13\0\1\13\4\0\3\13\1\0\13\13\1\201\11\13"+
    "\3\0\2\13\7\0\1\13\13\0\1\13\4\0\3\13"+
    "\1\0\11\13\1\202\13\13\3\0\2\13\7\0\1\13"+
    "\13\0\1\13\4\0\3\13\1\0\4\13\1\203\20\13"+
    "\3\0\2\13\7\0\1\13\13\0\1\13\4\0\3\13"+
    "\1\0\7\13\1\204\15\13\3\0\2\13\7\0\1\13"+
    "\13\0\1\13\4\0\3\13\1\0\15\13\1\205\7\13"+
    "\3\0\2\13\7\0\1\13\13\0\1\13\4\0\3\13"+
    "\1\0\2\13\1\206\22\13\3\0\2\13\7\0\1\13"+
    "\13\0\1\13\4\0\3\13\1\0\13\13\1\207\11\13"+
    "\3\0\2\13\7\0\1\13\13\0\1\13\4\0\3\13"+
    "\1\0\6\13\1\210\16\13\3\0\1\13\10\0\1\13"+
    "\20\0\3\13\1\0\25\13\4\0\1\13\7\0\1\13"+
    "\11\0\1\211\1\0\1\13\4\0\3\13\1\0\25\13"+
    "\3\0\2\13\7\0\1\13\13\0\1\13\4\0\3\13"+
    "\1\0\15\13\1\212\7\13\3\0\2\13\7\0\1\13"+
    "\13\0\1\13\4\0\3\13\1\0\20\13\1\213\4\13"+
    "\3\0\2\13\7\0\1\13\13\0\1\13\4\0\3\13"+
    "\1\0\13\13\1\214\11\13\3\0\2\13\7\0\1\13"+
    "\13\0\1\13\4\0\3\13\1\0\21\13\1\215\3\13"+
    "\3\0\1\13\51\0\1\216\14\0\1\13\7\0\1\13"+
    "\13\0\1\13\4\0\3\13\1\0\21\13\1\217\3\13"+
    "\3\0\2\13\7\0\1\13\13\0\1\13\4\0\3\13"+
    "\1\0\15\13\1\220\7\13\3\0\2\13\7\0\1\13"+
    "\13\0\1\13\4\0\3\13\1\0\20\13\1\221\4\13"+
    "\3\0\1\13\56\0\1\222\7\0\1\13\7\0\1\13"+
    "\13\0\1\13\4\0\3\13\1\0\20\13\1\223\4\13"+
    "\3\0\2\13\7\0\1\13\13\0\1\13\4\0\3\13"+
    "\1\0\21\13\1\224\3\13\3\0\1\13\55\0\1\225"+
    "\10\0\1\13\7\0\1\13\13\0\1\13\4\0\3\13"+
    "\1\0\20\13\1\226\4\13\3\0\1\13\22\0\1\227"+
    "\115\0\1\230\66\0\1\231\56\0\1\232\71\0\1\233"+
    "\72\0\1\234\56\0\1\235\67\0\1\236\15\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[6048];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** Error code for "Unknown internal scanner error". */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  /** Error code for "could not match input". */
  private static final int ZZ_NO_MATCH = 1;
  /** Error code for "pushback value was too large". */
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /**
   * Error messages for {@link #ZZ_UNKNOWN_ERROR}, {@link #ZZ_NO_MATCH}, and
   * {@link #ZZ_PUSHBACK_2BIG} respectively.
   */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state {@code aState}
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\4\0\1\11\4\1\1\11\3\1\6\11\1\1\1\11"+
    "\1\1\2\11\1\1\1\11\15\1\1\11\1\1\1\11"+
    "\1\1\1\11\1\1\2\11\1\1\3\11\1\0\1\11"+
    "\1\0\22\1\6\11\1\0\1\11\21\1\1\11\33\1"+
    "\1\0\12\1\1\0\4\1\1\0\3\1\1\0\2\1"+
    "\1\0\1\1\5\0\1\1\1\0\1\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[158];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** Input device. */
  private java.io.Reader zzReader;

  /** Current state of the DFA. */
  private int zzState;

  /** Current lexical state. */
  private int zzLexicalState = YYINITIAL;

  /**
   * This buffer contains the current text to be matched and is the source of the {@link #yytext()}
   * string.
   */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** Text position at the last accepting state. */
  private int zzMarkedPos;

  /** Current text position in the buffer. */
  private int zzCurrentPos;

  /** Marks the beginning of the {@link #yytext()} string in the buffer. */
  private int zzStartRead;

  /** Marks the last character in the buffer, that has been read from input. */
  private int zzEndRead;

  /**
   * Whether the scanner is at the end of file.
   * @see #yyatEOF
   */
  private boolean zzAtEOF;

  /**
   * The number of occupied positions in {@link #zzBuffer} beyond {@link #zzEndRead}.
   *
   * <p>When a lead/high surrogate has been read from the input stream into the final
   * {@link #zzBuffer} position, this will have a value of 1; otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /** Number of newlines encountered up to the start of the matched text. */
  private int yyline;

  /** Number of characters from the last newline up to the start of the matched text. */
  private int yycolumn;

  /** Number of characters up to the start of the matched text. */
  private long yychar;

  /** Whether the scanner is currently at the beginning of a line. */
  @SuppressWarnings("unused")
  private boolean zzAtBOL = true;

  /** Whether the user-EOF-code has already been executed. */
  private boolean zzEOFDone;

  /* user code: */
    StringBuffer string = new StringBuffer();
    public boolean add=true;
    public int fila,columna=0;
     public ErrorClass errores=new ErrorClass();
    private Symbol symbol(int type, Object value) {
        fila=yyline+1;
        columna+=yycolumn+1;

        return new Symbol(type, yyline+1, yycolumn+1, value);
    }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public javalex(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Translates raw input code points to DFA table row
   */
  private static int zzCMap(int input) {
    return ZZ_CMAP[input];
  }

  /**
   * Refills the input buffer.
   *
   * @return {@code false} iff there was new input.
   * @exception java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead - zzStartRead);

      /* translate stored positions */
      zzEndRead -= zzStartRead;
      zzCurrentPos -= zzStartRead;
      zzMarkedPos -= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length * 2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException(
          "Reader returned 0 characters. See JFlex examples/zero-reader for a workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
        if (numRead == requested) { // We requested too few chars to encode a full Unicode character
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        } else {                    // There is room in the buffer for at least one more char
          int c = zzReader.read();  // Expecting to read a paired low surrogate char
          if (c == -1) {
            return true;
          } else {
            zzBuffer[zzEndRead++] = (char)c;
          }
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }


  /**
   * Closes the input reader.
   *
   * @throws java.io.IOException if the reader could not be closed.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true; // indicate end of file
    zzEndRead = zzStartRead; // invalidate buffer

    if (zzReader != null) {
      zzReader.close();
    }
  }


  /**
   * Resets the scanner to read from a new input stream.
   *
   * <p>Does not close the old reader.
   *
   * <p>All internal variables are reset, the old input stream <b>cannot</b> be reused (internal
   * buffer is discarded and lost). Lexical state is set to {@code ZZ_INITIAL}.
   *
   * <p>Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader The new input stream.
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzEOFDone = false;
    yyResetPosition();
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE) {
      zzBuffer = new char[ZZ_BUFFERSIZE];
    }
  }

  /**
   * Resets the input position.
   */
  private final void yyResetPosition() {
      zzAtBOL  = true;
      zzAtEOF  = false;
      zzCurrentPos = 0;
      zzMarkedPos = 0;
      zzStartRead = 0;
      zzEndRead = 0;
      zzFinalHighSurrogate = 0;
      yyline = 0;
      yycolumn = 0;
      yychar = 0L;
  }


  /**
   * Returns whether the scanner has reached the end of the reader it reads from.
   *
   * @return whether the scanner has reached EOF.
   */
  public final boolean yyatEOF() {
    return zzAtEOF;
  }


  /**
   * Returns the current lexical state.
   *
   * @return the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state.
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   *
   * @return the matched text.
   */
  public final String yytext() {
    return new String(zzBuffer, zzStartRead, zzMarkedPos-zzStartRead);
  }


  /**
   * Returns the character at the given position from the matched text.
   *
   * <p>It is equivalent to {@code yytext().charAt(pos)}, but faster.
   *
   * @param position the position of the character to fetch. A value from 0 to {@code yylength()-1}.
   *
   * @return the character at {@code position}.
   */
  public final char yycharat(int position) {
    return zzBuffer[zzStartRead + position];
  }


  /**
   * How many characters were matched.
   *
   * @return the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occurred while scanning.
   *
   * <p>In a well-formed scanner (no or only correct usage of {@code yypushback(int)} and a
   * match-all fallback rule) this method will only be called with things that
   * "Can't Possibly Happen".
   *
   * <p>If this method is called, something is seriously wrong (e.g. a JFlex bug producing a faulty
   * scanner etc.).
   *
   * <p>Usual syntax/scanner level error handling should be done in error fallback rules.
   *
   * @param errorCode the code of the error message to display.
   */
  private static void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    } catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * <p>They will be read again by then next call of the scanning method.
   *
   * @param number the number of characters to be read again. This number must not be greater than
   *     {@link #yylength()}.
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
    
  yyclose();    }
  }




  /**
   * Resumes scanning until the next regular expression is matched, the end of input is encountered
   * or an I/O-Error occurs.
   *
   * @return the next token.
   * @exception java.io.IOException if any I/O-Error occurs.
   */
  @Override  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char[] zzBufferL = zzBuffer;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      yychar+= zzMarkedPosL-zzStartRead;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':  // fall through
        case '\u000C':  // fall through
        case '\u0085':  // fall through
        case '\u2028':  // fall through
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is
        // (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof)
            zzPeek = false;
          else
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMap(zzInput) ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
          { return new java_cup.runtime.Symbol(sym.EOF); }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1:
            { if(add){
        fila=yyline+1;
          columna+=yycolumn+1;
        errores.AddError(0,yyline+1,yycolumn+1,yytext());}
            }
            // fall through
          case 58: break;
          case 2:
            { 
            }
            // fall through
          case 59: break;
          case 3:
            { if(add){ return symbol(sym.NOT,new String(yytext()));}
            }
            // fall through
          case 60: break;
          case 4:
            { yybegin(VERIFY);
            }
            // fall through
          case 61: break;
          case 5:
            { if(add){return symbol(sym.ID,new String(yytext()));}
            }
            // fall through
          case 62: break;
          case 6:
            { if(add){return symbol(sym.MODUL,new String(yytext()));}
            }
            // fall through
          case 63: break;
          case 7:
            { yybegin(STRING);
            }
            // fall through
          case 64: break;
          case 8:
            { if(add){return symbol(sym.AP,new String(yytext()));}
            }
            // fall through
          case 65: break;
          case 9:
            { if(add){return symbol(sym.CP,new String(yytext()));}
            }
            // fall through
          case 66: break;
          case 10:
            { if(add){return symbol(sym.MUL,new String(yytext()));}
            }
            // fall through
          case 67: break;
          case 11:
            { if(add){return symbol(sym.SUM,new String(yytext()));}
            }
            // fall through
          case 68: break;
          case 12:
            { if(add){return symbol(sym.COMA,new String(yytext()));}
            }
            // fall through
          case 69: break;
          case 13:
            { if(add){return symbol(sym.RES,new String(yytext()));}
            }
            // fall through
          case 70: break;
          case 14:
            { if(add){return symbol(sym.DIV,new String(yytext()));}
            }
            // fall through
          case 71: break;
          case 15:
            { if(add){return symbol(sym.ENTERO,new Integer(yytext()));}
            }
            // fall through
          case 72: break;
          case 16:
            { if(add){return symbol(sym.BOTHPOINT,new String(yytext()));}
            }
            // fall through
          case 73: break;
          case 17:
            { if(add){return symbol(sym.PUNTOCOMA,new String(yytext()));}
            }
            // fall through
          case 74: break;
          case 18:
            { if(add){return symbol(sym.OP,new String(yytext()));}
            }
            // fall through
          case 75: break;
          case 19:
            { if(add){return symbol(sym.EQUALS,new String(yytext()));}
            }
            // fall through
          case 76: break;
          case 20:
            { if(add){return symbol(sym.AC,new String(yytext()));}
            }
            // fall through
          case 77: break;
          case 21:
            { if(add){return symbol(sym.CC,new String(yytext()));}
            }
            // fall through
          case 78: break;
          case 22:
            { string.append(yytext());yybegin(VERIFY);
            }
            // fall through
          case 79: break;
          case 23:
            { yybegin(YYINITIAL);
                                        String val=string.toString();
                                        string=new StringBuffer();
                                      if(add){  return symbol(sym.CADENA,val);}
            }
            // fall through
          case 80: break;
          case 24:
            { string.append(yytext());yybegin(CHAR);
            }
            // fall through
          case 81: break;
          case 25:
            { yybegin(YYINITIAL); {}
            }
            // fall through
          case 82: break;
          case 26:
            { string=new StringBuffer();yybegin(STRING);
            }
            // fall through
          case 83: break;
          case 27:
            { yybegin(YYINITIAL);
                                     String val=string.toString();
                                     string=new StringBuffer();
                                     char y=val.charAt(0);
                                   if(add){  return symbol(sym.CARACTER,y);}
            }
            // fall through
          case 84: break;
          case 28:
            { if(add){return symbol(sym.AND,new String(yytext()));}
            }
            // fall through
          case 85: break;
          case 29:
            { if(add){return symbol(sym.DO,new String(yytext()));}
            }
            // fall through
          case 86: break;
          case 30:
            { if(add){return symbol(sym.IF,new String(yytext()));}
            }
            // fall through
          case 87: break;
          case 31:
            { if(add){return symbol(sym.OR,new String(yytext()));}
            }
            // fall through
          case 88: break;
          case 32:
            { string.append('\"'); yybegin(VERIFY);
            }
            // fall through
          case 89: break;
          case 33:
            { string.append('\"'); yybegin(CHAR);
            }
            // fall through
          case 90: break;
          case 34:
            { string.append('\n'); yybegin(CHAR);
            }
            // fall through
          case 91: break;
          case 35:
            { string.append('\r'); yybegin(CHAR);
            }
            // fall through
          case 92: break;
          case 36:
            { string.append('\t'); yybegin(CHAR);
            }
            // fall through
          case 93: break;
          case 37:
            { if(add){return symbol(sym.REAL,new Double(yytext()));}
            }
            // fall through
          case 94: break;
          case 38:
            { if(add){return symbol(sym.FOR,new String(yytext()));}
            }
            // fall through
          case 95: break;
          case 39:
            { if(add){return symbol(sym.INT,new String(yytext()));}
            }
            // fall through
          case 96: break;
          case 40:
            { if(add){add=false;
      yyclose();
     return symbol(sym.PY,new String(yytext()));}
            }
            // fall through
          case 97: break;
          case 41:
            { if(add){return symbol(sym.CASE,new String(yytext()));}
            }
            // fall through
          case 98: break;
          case 42:
            { if(add){return symbol(sym.CHAR,new String(yytext()));}
            }
            // fall through
          case 99: break;
          case 43:
            { if(add){return symbol(sym.ELSE,new String(yytext()));}
            }
            // fall through
          case 100: break;
          case 44:
            { if(add){return symbol(sym.VOID,new String(yytext()));}
            }
            // fall through
          case 101: break;
          case 45:
            { if(add){return symbol(sym.BREAK,new String(yytext()));}
            }
            // fall through
          case 102: break;
          case 46:
            { if(add){return symbol(sym.CLASS,new String(yytext()));}
            }
            // fall through
          case 103: break;
          case 47:
            { if(add){return symbol(sym.DOUBLE,new String(yytext()));}
            }
            // fall through
          case 104: break;
          case 48:
            { if(add){return symbol(sym.WHILE,new String(yytext()));}
            }
            // fall through
          case 105: break;
          case 49:
            { if(add){return symbol(sym.PUBLIC,new String(yytext()));}
            }
            // fall through
          case 106: break;
          case 50:
            { if(add){return symbol(sym.RETURN,new String(yytext()));}
            }
            // fall through
          case 107: break;
          case 51:
            { if(add){return symbol(sym.SWITCH,new String(yytext()));}
            }
            // fall through
          case 108: break;
          case 52:
            { if(add){return symbol(sym.DEFAULT,new String(yytext()));}
            }
            // fall through
          case 109: break;
          case 53:
            { if(add){return symbol(sym.INPUTI,new String(yytext()));}
            }
            // fall through
          case 110: break;
          case 54:
            { if(add){return symbol(sym.INPUTC,new String(yytext()));}
            }
            // fall through
          case 111: break;
          case 55:
            { if(add){return symbol(sym.INPUTD,new String(yytext()));}
            }
            // fall through
          case 112: break;
          case 56:
            { if(add){return symbol(sym.PRINT,new String(yytext()));}
            }
            // fall through
          case 113: break;
          case 57:
            { if(add){return symbol(sym.PRINTLN,new String(yytext()));}
            }
            // fall through
          case 114: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }

  /**
   * Converts an int token code into the name of the
   * token by reflection on the cup symbol class/interface sym
   */
  private static String getTokenName(int token) {
    try {
      java.lang.reflect.Field [] classFields = sym.class.getFields();
      for (int i = 0; i < classFields.length; i++) {
        if (classFields[i].getInt(null) == token) {
          return classFields[i].getName();
        }
      }
    } catch (Exception e) {
      e.printStackTrace(System.err);
    }

    return "UNKNOWN TOKEN";
  }

  /**
   * Same as next_token but also prints the token to standard out
   * for debugging.
   */
  public java_cup.runtime.Symbol debug_next_token() throws java.io.IOException {
    java_cup.runtime.Symbol s = next_token();
    System.out.println( "line:" + (yyline+1) + " col:" + (yycolumn+1) + " char:" + yychar + " --"+ yytext() + "--" + getTokenName(s.sym) + "--");
    return s;
  }

  /**
   * Runs the scanner on input files.
   *
   * This main method is the debugging routine for the scanner.
   * It prints debugging information about each returned token to
   * System.out until the end of file is reached, or an error occured.
   *
   * @param argv   the command line, contains the filenames to run
   *               the scanner on.
   */
  public static void main(String[] argv) {
    if (argv.length == 0) {
      System.out.println("Usage : java javalex [ --encoding <name> ] <inputfile(s)>");
    }
    else {
      int firstFilePos = 0;
      String encodingName = "UTF-8";
      if (argv[0].equals("--encoding")) {
        firstFilePos = 2;
        encodingName = argv[1];
        try {
          // Side-effect: is encodingName valid?
          java.nio.charset.Charset.forName(encodingName);
        } catch (Exception e) {
          System.out.println("Invalid encoding '" + encodingName + "'");
          return;
        }
      }
      for (int i = firstFilePos; i < argv.length; i++) {
        javalex scanner = null;
        try {
          java.io.FileInputStream stream = new java.io.FileInputStream(argv[i]);
          java.io.Reader reader = new java.io.InputStreamReader(stream, encodingName);
          scanner = new javalex(reader);
          while ( !scanner.zzAtEOF ) scanner.debug_next_token();
        }
        catch (java.io.FileNotFoundException e) {
          System.out.println("File not found : \""+argv[i]+"\"");
        }
        catch (java.io.IOException e) {
          System.out.println("IO error scanning file \""+argv[i]+"\"");
          System.out.println(e);
        }
        catch (Exception e) {
          System.out.println("Unexpected exception:");
          e.printStackTrace();
        }
      }
    }
  }


}
