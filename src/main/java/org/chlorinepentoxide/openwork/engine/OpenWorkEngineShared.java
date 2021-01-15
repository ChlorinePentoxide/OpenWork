package org.chlorinepentoxide.openwork.engine;

import org.chlorinepentoxide.openwork.graphics.OpenWorkTexture;
import org.chlorinepentoxide.openwork.parallel.OpenWorkProcessorController;
import org.chlorinepentoxide.openwork.utility.OpenWorkDiagnostics;
import org.chlorinepentoxide.openwork.utility.OpenWorkLogSystem;

import java.util.Vector;

public class OpenWorkEngineShared {

    public Vector<OpenWorkTexture> textures;

    public OpenWorkLogSystem logger;

    public OpenWorkDiagnostics diagnostics;

    public int windowHeight;

    public int windowWidth;

    public int exitstatus = 0;

}
