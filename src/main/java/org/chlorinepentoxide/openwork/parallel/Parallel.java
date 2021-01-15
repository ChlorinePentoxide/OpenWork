package org.chlorinepentoxide.openwork.parallel;

import org.chlorinepentoxide.openwork.graphics.OpenWorkColor;
import org.chlorinepentoxide.openwork.graphics.OpenWorkTexture;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public final class Parallel {

    private Parallel() {}

    public static class Textures {

        public static void paint(int offsetX, int offsetY, int finalX, int finalY, @NotNull OpenWorkColor color, @NotNull OpenWorkTexture... textures) throws InterruptedException {
            List<Callable<Void>> tasks = new ArrayList<>();
            for(OpenWorkTexture texture:textures) {
                tasks.add( () -> {
                    for(int i=offsetX;i<finalX;i++) {
                        for(int j=offsetY;j<finalY;j++) {
                            texture.setColor(color, i, j);
                        }
                    }
                    return null;
                });
            }
            OpenWorkProcessorController.execute(tasks);
        }

        public static void paint(int offsetX, int offsetY, @NotNull OpenWorkColor color, @NotNull OpenWorkTexture... textures) throws InterruptedException {
            List<Callable<Void>> tasks = new ArrayList<>();
            for(OpenWorkTexture texture:textures) {
                tasks.add( () -> {
                    for(int i=offsetX;i<texture.getWidth();i++) {
                        for(int j=offsetY;j<texture.getHeight();j++) {
                            texture.setColor(color, i, j);
                        }
                    }
                    return null;
                });
            }
            OpenWorkProcessorController.execute(tasks);
        }

        public static void paint(@NotNull OpenWorkColor color, @NotNull OpenWorkTexture... textures) throws InterruptedException {
            paint(0, 0, color, textures);
        }

        public static OpenWorkTexture[] load(@NotNull String... files) throws InterruptedException {
            OpenWorkTexture[] textures = new OpenWorkTexture[files.length];
            List<Callable<Void>> tasks = new ArrayList<>();
            for (int i = 0; i < textures.length; i++) {
                final int fi = i;
                tasks.add( () -> {
                    textures[fi] = new OpenWorkTexture(ImageIO.read(new File(files[fi])));
                    return null;
                });
            }
            OpenWorkProcessorController.execute(tasks);
            return textures;
        }

        public static OpenWorkTexture[] load(@NotNull URI... uris) throws InterruptedException {
            OpenWorkTexture[] textures = new OpenWorkTexture[uris.length];
            List<Callable<Void>> tasks = new ArrayList<>();
            for (int i = 0; i < textures.length; i++) {
                final int fi = i;
                tasks.add( () -> {
                    textures[fi] = new OpenWorkTexture(ImageIO.read(new File(uris[fi])));
                    return null;
                });
            }
            OpenWorkProcessorController.execute(tasks);
            return textures;
        }

    }

    public static class Geometry {

    }

}
