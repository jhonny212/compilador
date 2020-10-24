package GramaticaVisualBasic;
import Errores.ErrorClass;
import java_cup.runtime.Symbol;
%%
%class vblex
%cup
%cupdebug
%line
%column
%full
%char
%public
%{
    StringBuffer string = new StringBuffer();
    public int fila,columna=0;
    public boolean add=true;
    public ErrorClass errores=new ErrorClass();
    private Symbol symbol(int type, Object value) {
        fila=yyline+1;
          columna+=yycolumn+1;
        return new Symbol(type, yyline+1, yycolumn+1, value);
    }
%}
numero=[0-9]
letra=[a-zA-Z]

InputCharacter = [^\r\n]
Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}
TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment     = "//" {InputCharacter}* ("\r"|"\n"|"\r\n")?
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )*
Identifier = [:jletter:] [:jletterdigit:]*
%state VERIFY
%state STRING
%state CHAR

%%
<YYINITIAL>{
    "\""
    {yybegin(VERIFY);}
    "Then"      { if(add){ return symbol(sym.THEN,new String(yytext()));}}
    "Dim"       {if(add){ return symbol(sym.DIM,new String(yytext()));}}
    "If"        {if(add){ return symbol(sym.IF,new String(yytext()));}}
    "End"       {if(add){ return symbol(sym.END,new String(yytext()));}}
    "%%VB"       {if(add){ return symbol(sym.VB,new String(yytext()));}}
    "%%JAVA"       {if(add){
    yyclose();
    add=false; return symbol(sym.JAVA,new String(yytext()));
    }}

    "ElseIf"    {if(add){ return symbol(sym.ELSE_IF,new String(yytext()));}}
    "Else"      {if(add){ return symbol(sym.ELSE,new String(yytext()));}}
    "Select"    {if(add){ return symbol(sym.SELECT,new String(yytext()));}}
    "Exit"      {if(add){ return symbol(sym.EXIT,new String(yytext()));}}
    "Case"      {if(add){ return symbol(sym.CASE,new String(yytext()));}}
    "Do"        {if(add){ return symbol(sym.DO,new String(yytext()));}}
    "Until"     {if(add){ return symbol(sym.UNTIL,new String(yytext()));}}
    "Loop"      {if(add){ return symbol(sym.LOOP,new String(yytext()));}}
    "Public"    {if(add){ return symbol(sym.PUBLIC,new String(yytext()));}}
    "Function"  {if(add){ return symbol(sym.FUN,new String(yytext()));}}
    "Sub"       {if(add){ return symbol(sym.SUB,new String(yytext()));}}
    "Module"    {if(add){ return symbol(sym.MODULE,new String(yytext()));}}
    "ByVal"     {if(add){ return symbol(sym.BYVAL,new String(yytext()));}}
    "intinput"   {if(add){ return symbol(sym.INPUTI,new String(yytext()));}}
    "floatinput" {if(add){ return symbol(sym.INPUTD,new String(yytext()));}}
    "charinput"  {if(add){ return symbol(sym.INPUTC,new String(yytext()));}}
    "Console.WriteLine"  {if(add){ return symbol(sym.CONSLN,new String(yytext()));}}
    "Console.Write"      {if(add){ return symbol(sym.CONSL,new String(yytext()));}}
    "To"        {if(add){ return symbol(sym.TO,new String(yytext()));}}

    "As"                {if(add){ return symbol(sym.AS,new String(yytext()));}}
    "="                 {if(add){ return symbol(sym.EQUALS,new String(yytext()));}}
    ","                 {if(add){ return symbol(sym.COMA,new String(yytext()));}}
    "Integer"           {if(add){ return symbol(sym.INT,new String(yytext()));}}
    "Next"              {if(add){ return symbol(sym.NEXT,new String(yytext()));}}
    "For"               {if(add){ return symbol(sym.FOR,new String(yytext()));}}
    "Step"              {if(add){ return symbol(sym.STEP,new String(yytext()));}}
    "While"             {if(add){ return symbol(sym.WHILE,new String(yytext()));}}
    "Return"            {if(add){ return symbol(sym.RETURN,new String(yytext()));}}
    "Char"              {if(add){ return symbol(sym.CHAR,new String(yytext()));}}
    "Double"            {if(add){ return symbol(sym.FLOAT,new String(yytext()));}}
    "Not"               {if(add){ return symbol(sym.NOT,new String(yytext()));}}

    (("\n")+(("\n")|("\t")|(" ")|{Comment})*)  {if(add){ return symbol(sym.SALTO_LINEA,new String(yytext()));}}

    "\t"
    {}
    " "
    {}
    "Or"                        {if(add){ return symbol(sym.OR,new String(yytext()));}}
    "And"                       {if(add){ return symbol(sym.AND,new String(yytext()));}}
    ">"|"<"|">="|"<="|"!="|"=="      {if(add){ return symbol(sym.OP,new String(yytext()));}}
    "+"                         {if(add){ return symbol(sym.SUM,new String(yytext()));}}
    "-"                         {if(add){ return symbol(sym.RES,new String(yytext()));}}
    {Comment}                   {}
    "/"                         {if(add){ return symbol(sym.DIV,new String(yytext()));}}
    "*"                         {if(add){ return symbol(sym.MUL,new String(yytext()));}}
    "%"                         {if(add){ return symbol(sym.MODUL,new String(yytext()));}}
    "("                         {if(add){ return symbol(sym.AP,new String(yytext()));}}
    ")"                         {if(add){ return symbol(sym.CP,new String(yytext()));}}
    "&"                         {if(add){ return symbol(sym.Y,new String(yytext()));}}
    {Identifier}                {if(add){ return symbol(sym.ID,new String(yytext()));}}
    ({numero})+(".")({numero})+         {if(add){ return symbol(sym.REAL,new Double(yytext()));}}
    ({numero})+                        {if(add){ return symbol(sym.ENTERO,new Integer(yytext()));}}
    .   {
    if(add){
    fila=yyline+1;
      columna+=yycolumn+1;
    errores.AddError(0,yyline+1,yycolumn+1,yytext());}}



}

  <VERIFY> {
      \"                            {   yybegin(YYINITIAL);
                                        String val=string.toString();
                                        string=new StringBuffer();
                                        if(add){ return symbol(sym.CADENA,val);}
                                    }
      [^\"]                         {string.append(yytext());yybegin(CHAR);}
      \\t                           { string.append('\t'); yybegin(CHAR); }
      \\n                           { string.append('\n'); yybegin(CHAR); }

      \\r                           { string.append('\r'); yybegin(CHAR);}
      \\\"                          { string.append('\"'); yybegin(CHAR);}
      \\                            { string.append('\\'); yybegin(CHAR);}
}

<CHAR>{
    \"                            { yybegin(YYINITIAL);
                                        String val=string.toString();
                                        string=new StringBuffer();
                                        char y=val.charAt(0);
                                      if(add){   return symbol(sym.CARACTER,y);}}
    [^\"]                         {string.append(yytext());yybegin(STRING);}
    \\t                           { string.append('\t'); yybegin(STRING); }
    \\n                           { string.append('\n'); yybegin(STRING); }

    \\r                           { string.append('\r'); yybegin(STRING);}
    \\\"                          { string.append('\"'); yybegin(STRING);}
    \\                            { string.append('\\'); yybegin(STRING);}
}
<STRING>{
    \"                            { yybegin(YYINITIAL);
                                        String val=string.toString();
                                        string=new StringBuffer();
                                    if(add){     return symbol(sym.CADENA,val);}}
    [^\"]+                         {string.append(yytext());yybegin(STRING);}
    \\t                           { string.append('\t'); yybegin(STRING); }
    \\n                           { string.append('\n'); yybegin(STRING); }

    \\r                           { string.append('\r'); yybegin(STRING);}
    \\\"                          { string.append('\"'); yybegin(STRING);}
    \\                            { string.append('\\'); yybegin(STRING);}
}