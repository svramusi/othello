-injars lib/ramusivich.jar
-outjars lib/ramusivich_obf.jar

-libraryjars /usr/lib/jvm/java-6-openjdk-amd64/jre/lib/rt.jar
-libraryjars lib/othello.jar

-keep public class students.ramusivich.StevesOthelloPlayer {
	public StevesOthelloPlayer(java.lang.String);
}

-overloadaggressively
