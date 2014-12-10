package sde;

import javassist.*;
import javassist.compiler.MemberResolver;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

import java.io.*;

public class Main {

    private static String[] parserClasses() {
        return new String[] {
                "org.bitstorm.gameoflife.AppletFrame$1",
                "org.bitstorm.gameoflife.AppletFrame$2",
                "org.bitstorm.gameoflife.AppletFrame$3",
                "org.bitstorm.gameoflife.AppletFrame$4",
                "org.bitstorm.gameoflife.AppletFrame$5",
                "org.bitstorm.gameoflife.AppletFrame$6",
                "org.bitstorm.gameoflife.AppletFrame",
                "org.bitstorm.gameoflife.Cell",
                "org.bitstorm.gameoflife.CellGrid",
                "org.bitstorm.gameoflife.CellGridCanvas$1",
                "org.bitstorm.gameoflife.CellGridCanvas$2",
                "org.bitstorm.gameoflife.CellGridCanvas$3",
                "org.bitstorm.gameoflife.CellGridCanvas",
                "org.bitstorm.gameoflife.GameOfLife",
                "org.bitstorm.gameoflife.GameOfLifeControls$1",
                "org.bitstorm.gameoflife.GameOfLifeControls$2",
                "org.bitstorm.gameoflife.GameOfLifeControls$3",
                "org.bitstorm.gameoflife.GameOfLifeControls$4",
                "org.bitstorm.gameoflife.GameOfLifeControls$5",
                "org.bitstorm.gameoflife.GameOfLifeControls",
                "org.bitstorm.gameoflife.GameOfLifeControlsEvent",
                "org.bitstorm.gameoflife.GameOfLifeControlsListener",
                "org.bitstorm.gameoflife.GameOfLifeGrid",
                "org.bitstorm.gameoflife.Shape$1",
                "org.bitstorm.gameoflife.Shape",
                "org.bitstorm.gameoflife.ShapeCollection",
                "org.bitstorm.gameoflife.ShapeException",
                "org.bitstorm.gameoflife.StandaloneGameOfLife$GameOfLifeGridIO",
                "org.bitstorm.gameoflife.StandaloneGameOfLife$MyDropListener",
                "org.bitstorm.gameoflife.StandaloneGameOfLife",
                "org.bitstorm.util.AboutDialog$1",
                "org.bitstorm.util.AboutDialog",
                "org.bitstorm.util.AlertBox$1",
                "org.bitstorm.util.AlertBox",
                "org.bitstorm.util.EasyFile",
                "org.bitstorm.util.ImageComponent",
                "org.bitstorm.util.LineEnumerator",
                "org.bitstorm.util.TextEnumerator",
                "org.bitstorm.util.TextFileDialog$1",
                "org.bitstorm.util.TextFileDialog"
        };
    }

    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        pool.importPackage("java.io");
        pool.insertClassPath("lib/GameOfLife.jar");
        for (String klass : parserClasses()) {
            CtClass ctClass = pool.get(klass);
            ctClass.defrost();

            if (ctClass.isAnnotation() || ctClass.isArray() || ctClass.isEnum() || ctClass.isFrozen() || ctClass.isInterface() || ctClass.isPrimitive())
                continue;

            for (CtMethod method : ctClass.getDeclaredMethods()) {
                if (method.isEmpty()) continue;

                method.addLocalVariable("start", CtClass.longType);
                method.addLocalVariable("external", CtClass.longType);
                method.addLocalVariable("calls", CtClass.longType);

                method.insertBefore("start = System.currentTimeMillis();");
                method.insertBefore("external = (long)0;");
                method.insertBefore("calls = (long)0;");

                method.instrument(new ExprEditor() {
                    public void edit(MethodCall m) throws CannotCompileException {
                        m.replace("long extStart = System.currentTimeMillis(); {calls = calls + 1; $_ = $proceed($$); external = external + (System.currentTimeMillis() - extStart) ;}");
                    }
                });

                method.insertAfter(
                    "long total = System.currentTimeMillis() - start;" +
                    "if (total > (long)0) {" +
                        "System.out.println(\"" + method.getLongName() + "\"" + ");" +
                        "System.out.println(\"calls = \" + calls);" +
                        "System.out.println(\"total = \" + total);" +
                        "System.out.println(\"prime = \" + (total - external));" +
                    "}"
                );


            }
            ctClass.writeFile("target");
        }


        System.out.println("DONE! YEEA!");
    }
}
