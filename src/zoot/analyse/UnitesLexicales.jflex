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
"fonctions"            { return symbol(CodesLexicaux.FONCTIONS); }
"debut"                { return symbol(CodesLexicaux.DEBUT); }
"retourne"             { return symbol(CodesLexicaux.RETOURNE); }
"fin"              	   { return symbol(CodesLexicaux.FIN); }
"ecrire"               { return symbol(CodesLexicaux.ECRIRE); }
"non"                    {return symbol(CodesLexicaux.NON);}
"si"                    {return symbol(CodesLexicaux.SI);}
"sinon"                    {return symbol(CodesLexicaux.SINON);}
"finsi"                    {return symbol(CodesLexicaux.FINSI);}
"et"                    {return symbol(CodesLexicaux.ET);}
"ou"                    {return symbol(CodesLexicaux.OU);}
"repeter"                    {return symbol(CodesLexicaux.REPETER);}
"jusqua"                    {return symbol(CodesLexicaux.JUSQUA);}
"finrepeter"                    {return symbol(CodesLexicaux.FINREPETER);}


"("                    { return symbol(CodesLexicaux.OUVRE); }
")"                    { return symbol(CodesLexicaux.FERME); }
";"                    { return symbol(CodesLexicaux.POINTVIRGULE); }
"="                    { return symbol(CodesLexicaux.AFF); }
","                    {return symbol(CodesLexicaux.VIRGULE);}
"=="                    {return symbol(CodesLexicaux.EGALE);}
"!="                    {return symbol(CodesLexicaux.DIFF);}
"+"                    {return symbol(CodesLexicaux.ADD);}
"*"                    {return symbol(CodesLexicaux.MULT);}
"-"                    {return symbol(CodesLexicaux.SOUS);}
"<"                    {return symbol(CodesLexicaux.INF);}



{type}                 { return symbol(CodesLexicaux.TYPE, yytext());}

{csteB}                { return symbol(CodesLexicaux.CSTBOOLEEN, yytext()); }
{csteE}      	       { return symbol(CodesLexicaux.CSTENTIERE, yytext()); }
{idf}                  { return symbol(CodesLexicaux.IDF, yytext());}

{espace}               { }
.                      { throw new AnalyseLexicaleException(yyline, yycolumn, yytext()) ; }

