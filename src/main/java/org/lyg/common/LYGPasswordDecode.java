package org.lyg.common;

public class LYGPasswordDecode{
	public String LYGPasswordCodeFront(String value){
		String output="";
		for(int i=0;i<value.length();i++){

			if(value.charAt(i)=="a".charAt(0)){
				output=output+"z";	
			}
			else if(value.charAt(i)=="b".charAt(0)){
				output=output+"y";	
			}
			else if(value.charAt(i)=="c".charAt(0)){
				output=output+"x";	
			}
			else if(value.charAt(i)=="d".charAt(0)){
				output=output+"w";	
			}
			else if(value.charAt(i)=="e".charAt(0)){
				output=output+"v";	
			}
			else if(value.charAt(i)=="f".charAt(0)){
				output=output+"u";	
			}
			else if(value.charAt(i)=="g".charAt(0)){
				output=output+"t";	
			}
			else if(value.charAt(i)=="h".charAt(0)){
				output=output+"s";	
			}
			else if(value.charAt(i)=="i".charAt(0)){
				output=output+"r";	
			}
			else if(value.charAt(i)=="j".charAt(0)){
				output=output+"q";	
			}
			else if(value.charAt(i)=="k".charAt(0)){
				output=output+"p";	
			}
			else if(value.charAt(i)=="l".charAt(0)){
				output=output+"o";	
			}
			else if(value.charAt(i)=="m".charAt(0)){
				output=output+"n";	
			}
			else if(value.charAt(i)=="n".charAt(0)){
				output=output+"m";	
			}
			else if(value.charAt(i)=="o".charAt(0)){
				output=output+"l";	
			}
			else if(value.charAt(i)=="p".charAt(0)){
				output=output+"k";	
			}
			else if(value.charAt(i)=="q".charAt(0)){
				output=output+"j";	
			}
			else if(value.charAt(i)=="r".charAt(0)){
				output=output+"i";	
			}
			else if(value.charAt(i)=="s".charAt(0)){
				output=output+"h";	
			}
			else if(value.charAt(i)=="t".charAt(0)){
				output=output+"g";	
			}
			else if(value.charAt(i)=="u".charAt(0)){
				output=output+"f";	
			}
			else if(value.charAt(i)=="v".charAt(0)){
				output=output+"e";	
			}
			else if(value.charAt(i)=="w".charAt(0)){
				output=output+"d";	
			}
			else if(value.charAt(i)=="x".charAt(0)){
				output=output+"c";	
			}
			else if(value.charAt(i)=="y".charAt(0)){
				output=output+"b";	
			}
			else if(value.charAt(i)=="z".charAt(0)){
				output=output+"a";	
			}
			else if(value.charAt(i)=="A".charAt(0)){
				output=output+"Z";	
			}
			else if(value.charAt(i)=="B".charAt(0)){
				output=output+"Y";	
			}
			else if(value.charAt(i)=="C".charAt(0)){
				output=output+"X";	
			}
			else if(value.charAt(i)=="D".charAt(0)){
				output=output+"W";	
			}
			else if(value.charAt(i)=="E".charAt(0)){
				output=output+"V";	
			}
			else if(value.charAt(i)=="F".charAt(0)){
				output=output+"U";	
			}
			else if(value.charAt(i)=="G".charAt(0)){
				output=output+"T";	
			}
			else if(value.charAt(i)=="H".charAt(0)){
				output=output+"S";	
			}
			else if(value.charAt(i)=="I".charAt(0)){
				output=output+"R";	
			}
			else if(value.charAt(i)=="J".charAt(0)){
				output=output+"Q";	
			}
			else if(value.charAt(i)=="K".charAt(0)){
				output=output+"P";	
			}
			else if(value.charAt(i)=="L".charAt(0)){
				output=output+"O";	
			}
			else if(value.charAt(i)=="M".charAt(0)){
				output=output+"N";	
			}
			else if(value.charAt(i)=="N".charAt(0)){
				output=output+"M";	
			}
			else if(value.charAt(i)=="O".charAt(0)){
				output=output+"L";	
			}
			else if(value.charAt(i)=="P".charAt(0)){
				output=output+"K";	
			}
			else if(value.charAt(i)=="Q".charAt(0)){
				output=output+"J";	
			}
			else if(value.charAt(i)=="R".charAt(0)){
				output=output+"I";	
			}
			else if(value.charAt(i)=="S".charAt(0)){
				output=output+"H";	
			}
			else if(value.charAt(i)=="T".charAt(0)){
				output=output+"G";	
			}
			else if(value.charAt(i)=="U".charAt(0)){
				output=output+"F";	
			}
			else if(value.charAt(i)=="V".charAt(0)){
				output=output+"E";	
			}
			else if(value.charAt(i)=="W".charAt(0)){
				output=output+"D";	
			}
			else if(value.charAt(i)=="X".charAt(0)){
				output=output+"C";	
			}
			else if(value.charAt(i)=="Y".charAt(0)){
				output=output+"B";	
			}
			else if(value.charAt(i)=="Z".charAt(0)){
				output=output+"A";	
			}
			else if(value.charAt(i)=="0".charAt(0)){
				output=output+"9";	
			}
			else if(value.charAt(i)=="1".charAt(0)){
				output=output+"8";	
			}
			else if(value.charAt(i)=="2".charAt(0)){
				output=output+"7";	
			}
			else if(value.charAt(i)=="3".charAt(0)){
				output=output+"6";	
			}
			else if(value.charAt(i)=="4".charAt(0)){
				output=output+"5";	
			}
			else if(value.charAt(i)=="5".charAt(0)){
				output=output+"4";	
			}
			else if(value.charAt(i)=="6".charAt(0)){
				output=output+"3";	
			}
			else if(value.charAt(i)=="7".charAt(0)){
				output=output+"2";	
			}
			else if(value.charAt(i)=="8".charAt(0)){
				output=output+"1";	
			}
			else if(value.charAt(i)=="9".charAt(0)){
				output=output+"0";	
			}else{
				output=output+value.charAt(i);	
			}
		}	
		return output;
	}
	public String LYGPasswordCodeBack(String value){
		String output="";
		for(int i=0;i<value.length();i++){
			if(value.charAt(i)=="a".charAt(0)){
				output=output+"o";	
			}
			else if(value.charAt(i)=="b".charAt(0)){
				output=output+"n";	
			}
			else if(value.charAt(i)=="c".charAt(0)){
				output=output+"m";	
			}
			else if(value.charAt(i)=="d".charAt(0)){
				output=output+"l";	
			}
			else if(value.charAt(i)=="e".charAt(0)){
				output=output+"k";	
			}
			else if(value.charAt(i)=="f".charAt(0)){
				output=output+"j";	
			}
			else if(value.charAt(i)=="g".charAt(0)){
				output=output+"i";	
			}
			else if(value.charAt(i)=="h".charAt(0)){
				output=output+"h";	
			}
			else if(value.charAt(i)=="i".charAt(0)){
				output=output+"g";	
			}
			else if(value.charAt(i)=="j".charAt(0)){
				output=output+"f";	
			}
			else if(value.charAt(i)=="k".charAt(0)){
				output=output+"e";	
			}
			else if(value.charAt(i)=="l".charAt(0)){
				output=output+"d";	
			}
			else if(value.charAt(i)=="m".charAt(0)){
				output=output+"c";	
			}
			else if(value.charAt(i)=="n".charAt(0)){
				output=output+"b";	
			}
			else if(value.charAt(i)=="o".charAt(0)){
				output=output+"a";	
			}
			else if(value.charAt(i)=="p".charAt(0)){
				output=output+"z";	
			}
			else if(value.charAt(i)=="q".charAt(0)){
				output=output+"y";	
			}
			else if(value.charAt(i)=="r".charAt(0)){
				output=output+"x";	
			}
			else if(value.charAt(i)=="s".charAt(0)){
				output=output+"w";	
			}
			else if(value.charAt(i)=="t".charAt(0)){
				output=output+"v";	
			}
			else if(value.charAt(i)=="u".charAt(0)){
				output=output+"u";	
			}
			else if(value.charAt(i)=="v".charAt(0)){
				output=output+"t";	
			}
			else if(value.charAt(i)=="w".charAt(0)){
				output=output+"s";	
			}
			else if(value.charAt(i)=="x".charAt(0)){
				output=output+"r";	
			}
			else if(value.charAt(i)=="y".charAt(0)){
				output=output+"q";	
			}
			else if(value.charAt(i)=="z".charAt(0)){
				output=output+"p";	
			}
			else if(value.charAt(i)=="A".charAt(0)){
				output=output+"O";	
			}
			else if(value.charAt(i)=="B".charAt(0)){
				output=output+"N";	
			}
			else if(value.charAt(i)=="C".charAt(0)){
				output=output+"M";	
			}
			else if(value.charAt(i)=="D".charAt(0)){
				output=output+"L";	
			}
			else if(value.charAt(i)=="E".charAt(0)){
				output=output+"K";	
			}
			else if(value.charAt(i)=="F".charAt(0)){
				output=output+"J";	
			}
			else if(value.charAt(i)=="G".charAt(0)){
				output=output+"I";	
			}
			else if(value.charAt(i)=="H".charAt(0)){
				output=output+"H";	
			}
			else if(value.charAt(i)=="I".charAt(0)){
				output=output+"G";	
			}
			else if(value.charAt(i)=="J".charAt(0)){
				output=output+"F";	
			}
			else if(value.charAt(i)=="K".charAt(0)){
				output=output+"E";	
			}
			else if(value.charAt(i)=="L".charAt(0)){
				output=output+"D";	
			}
			else if(value.charAt(i)=="M".charAt(0)){
				output=output+"C";	
			}
			else if(value.charAt(i)=="N".charAt(0)){
				output=output+"B";	
			}
			else if(value.charAt(i)=="O".charAt(0)){
				output=output+"A";	
			}
			else if(value.charAt(i)=="P".charAt(0)){
				output=output+"Z";	
			}
			else if(value.charAt(i)=="Q".charAt(0)){
				output=output+"Y";	
			}
			else if(value.charAt(i)=="R".charAt(0)){
				output=output+"X";	
			}
			else if(value.charAt(i)=="S".charAt(0)){
				output=output+"W";	
			}
			else if(value.charAt(i)=="T".charAt(0)){
				output=output+"V";	
			}
			else if(value.charAt(i)=="U".charAt(0)){
				output=output+"U";	
			}
			else if(value.charAt(i)=="V".charAt(0)){
				output=output+"T";	
			}
			else if(value.charAt(i)=="W".charAt(0)){
				output=output+"S";	
			}
			else if(value.charAt(i)=="X".charAt(0)){
				output=output+"R";	
			}
			else if(value.charAt(i)=="Y".charAt(0)){
				output=output+"Q";	
			}
			else if(value.charAt(i)=="Z".charAt(0)){
				output=output+"P";	
			}
			else if(value.charAt(i)=="0".charAt(0)){
				output=output+"5";	
			}
			else if(value.charAt(i)=="1".charAt(0)){
				output=output+"7";	
			}
			else if(value.charAt(i)=="2".charAt(0)){
				output=output+"8";	
			}
			else if(value.charAt(i)=="3".charAt(0)){
				output=output+"9";	
			}
			else if(value.charAt(i)=="4".charAt(0)){
				output=output+"6";	
			}
			else if(value.charAt(i)=="5".charAt(0)){
				output=output+"0";	
			}
			else if(value.charAt(i)=="6".charAt(0)){
				output=output+"4";	
			}
			else if(value.charAt(i)=="7".charAt(0)){
				output=output+"1";	
			}
			else if(value.charAt(i)=="8".charAt(0)){
				output=output+"2";	
			}
			else if(value.charAt(i)=="9".charAt(0)){
				output=output+"3";	
			}else{
				output=output+value.charAt(i);	
			}
		}	
		return output;
	}
}