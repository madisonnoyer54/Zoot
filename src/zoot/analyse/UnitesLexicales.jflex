package zoot.analyse ;

import java_cup.runtime.*;
import zoot.exceptions.AnalyseLexicaleException;

%%

%class AnalyseurLexical
%public

%line
%column

%type Symbol
%eofval{
        return symbol(CodesLexicaux.EOF) ;
%eofval}

%cup

%{

  private StringBuilder chaine ;

  private Symbol symbol(int type) {
	return new Symbol(type, yyline, yycolumn) ;
  }

  private Symbol symbol(int type, Object value) {
	return new Symbol(type, yyline, yycolumn, value) ;
  }
%}

csteE = [0-9]+
csteB = (vrai)|(faux)
idf = [a-zA-Z0-9_]+
finDeLigne = \r|\n
espace = {finDeLigne}  | [ \t\f]
type = (entier)|(booleen)

%%
"//".*                                    { /* DO NOTHING */ }


"variables"            { return symbol(CodesLexicaux.VARIABLES); }
"debut"                { return symbol(CodesLexicaux.DEBUT); }
"fin"              	   { return symbol(CodesLexicaux.FIN); }

"ecrire"               { return symbol(CodesLexicaux.ECRIRE); }



";"                    { return symbol(CodesLexicaux.POINTVIRGULE); }
"="                    { return symbol(CodesLexicaux.EGALE); }

{type}                 { return symbol(CodesLexicaux.TYPE, yytext());}

{csteB}                { return symbol(CodesLexicaux.CSTBOOLEEN, yytext()); }
{csteE}      	       { return symbol(CodesLexicaux.CSTENTIERE, yytext()); }
{idf}                  { return symbol(CodesLexicaux.IDF, yytext());}

{espace}               { }
.                      { throw new AnalyseLexicaleException(yyline, yycolumn, yytext()) ; }

