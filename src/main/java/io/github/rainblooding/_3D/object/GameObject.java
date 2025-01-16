package io.github.rainblooding._3D.object;

import io.github.rainblooding._3D.base.Move;
import io.github.rainblooding._3D.base._3DModel;

public interface GameObject extends Move {

    _3DModel get3DModel();
}
