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
letra = [a-z]
punto = "."
signos = [@,;!¡¿?=()\\+*-]
directorio = {letra}":"("\\"{contenido})+
nombre_carpeta = {letra}+
nombre_documento = ({letra}|{numero})+
formato_documento = {punto}({letra})+
contenido = ({signos}|{numero}|{letra})+

%%

/* PALABRAS RESERVADAS */ 

"<estructura>"   {return new Symbol(tabla_simbolos.abrir_estructura, yycolumn, yyline, new String(yytext()));} 
"</estructura>"	 {return new Symbol(tabla_simbolos.cerrar_estructura, yycolumn, yyline, new String(yytext()));}
"<directorio>"   {return new Symbol(tabla_simbolos.abrir_directorio, yycolumn, yyline, new String(yytext()));} 
"</directorio>"	 {return new Symbol(tabla_simbolos.cerrar_directorio, yycolumn, yyline, new String(yytext()));}
"<carpeta>"      {return new Symbol(tabla_simbolos.abrir_carpeta, yycolumn, yyline, new String(yytext()));} 	
"</carpeta>"     {return new Symbol(tabla_simbolos.cerrar_carpeta, yycolumn, yyline, new String(yytext()));} 
"<nombre>"       {return new Symbol(tabla_simbolos.abrir_nombre, yycolumn, yyline, new String(yytext()));} 
"</nombre>"      {return new Symbol(tabla_simbolos.cerrar_nombre, yycolumn, yyline, new String(yytext()));} 
"<documento>"    {return new Symbol(tabla_simbolos.abrir_documento, yycolumn, yyline, new String(yytext()));} 
"</documento>"   {return new Symbol(tabla_simbolos.cerrar_documento, yycolumn, yyline, new String(yytext()));} 
"<formato>"      {return new Symbol(tabla_simbolos.abrir_formato, yycolumn, yyline, new String(yytext()));}
"</formato>"     {return new Symbol(tabla_simbolos.cerrar_formato, yycolumn, yyline, new String(yytext()));} 
"<contenido>"    {return new Symbol(tabla_simbolos.abrir_contenido, yycolumn, yyline, new String(yytext()));}
"</contenido>"   {return new Symbol(tabla_simbolos.cerrar_contenido, yycolumn, yyline, new String(yytext()));}
{directorio}     {return new Symbol(tabla_simbolos.directorio, yycolumn,yyline,new String(yytext()));}
{nombre_carpeta}    {return new Symbol(tabla_simbolos.nombre_carpeta, yycolumn,yyline,new String(yytext()));}
{nombre_documento}  {return new Symbol(tabla_simbolos.nombre_documento, yycolumn,yyline,new String(yytext()));}
{formato_documento} {return new Symbol(tabla_simbolos.formato_documento, yycolumn,yyline,new String(yytext()));}
{contenido}         {return new Symbol(tabla_simbolos.contenido, yycolumn,yyline,new String(yytext()));} 

//para este caso no son necesarias palabras reservadas 

[ \t\r\f\n]+ { /* Se ignoran */}  

/* CUAQUIER OTRO */ 
.         {return new Symbol(tabla_simbolos.error, yycolumn,yyline, new String(yytext()));} 