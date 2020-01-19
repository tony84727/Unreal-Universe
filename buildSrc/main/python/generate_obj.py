import bpy
import sys

argv = sys.argv
argv = argv[argv.index("--") + 1:]  # get all args after "--"

bpy.ops.export_scene.obj(
    filepath=argv[0],
    check_existing=False,
    axis_forward='-Z',
    axis_up='Y',
    use_triangles=True,
    use_selection=True,
)
