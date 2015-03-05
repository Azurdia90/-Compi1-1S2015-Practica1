package Analizadores;
import	java_cup.runtime.Symbol;  

%%

%cupsym tabla_simbolos  
%class lexico           
%cup                    
%public                 
%unicode                
%line                   
%column                 
%char                   
%ignorecase     

numero = [0-9]
letra = [A-Za-z]
comillas = "\""
d_puntos = ":"
abrir = "<"
cerrar = ">"
fin = "/"
slash = "\\"
directorio = {letra}{d_puntos}({slash}{nombre_archivo})+
nombre_archivo = ({letra}|{numero})+
contenido = {comillas}[^]+ {comillas}

%%

/* PALABRAS RESERVADAS */ 

{directorio}     {return new Symbol(tabla_simbolos.directorio, yycolumn,yyline,new String(yytext()));}
{abrir}			 {return new Symbol(tabla_simbolos.abrir, yycolumn, yyline, new String(yytext()));} 
{cerrar}		 {return new Symbol(tabla_simbolos.cerrar, yycolumn, yyline, new String(yytext()));} 
{fin}	         {return new Symbol(tabla_simbolos.fin, yycolumn, yyline, new String(yytext()));}
"estructura"     {return new Symbol(tabla_simbolos.r_estructura, yycolumn, yyline, new String(yytext()));} 
"directorio"     {return new Symbol(tabla_simbolos.r_directorio, yycolumn, yyline, new String(yytext()));} 
"carpeta"        {return new Symbol(tabla_simbolos.r_carpeta, yycolumn, yyline, new String(yytext()));} 	
"nombre"         {return new Symbol(tabla_simbolos.r_nombre, yycolumn, yyline, new String(yytext()));} 
"documento"      {return new Symbol(tabla_simbolos.r_documento, yycolumn, yyline, new String(yytext()));} 
"formato"        {return new Symbol(tabla_simbolos.r_formato, yycolumn, yyline, new String(yytext()));}
"contenido"         {return new Symbol(tabla_simbolos.r_contenido, yycolumn, yyline, new String(yytext()));}
{nombre_archivo}    {return new Symbol(tabla_simbolos.nombre_archivo, yycolumn,yyline,new String(yytext()));}
{contenido}         {return new Symbol(tabla_simbolos.contenido, yycolumn,yyline,new String(yytext()));}
{comillas}			{return new Symbol(tabla_simbolos.comillas, yycolumn,yyline,new String(yytext()));}

[ \t\r\f\n]+ { /* Se ignoran */}  

/* CUAQUIER OTRO */ 
.         {return new Symbol(tabla_simbolos.error, yycolumn,yyline, new String(yytext()));} 