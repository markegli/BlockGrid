BlockGrid
=========

BlockGrid is a bukkit world generator, forked from [LRFLEW/SkyGrid][SkyGrid plugin].
It is based on the original SethBling [SkyGrid script][].

As with all custom generators, you can use this generator for your default world.
Just add the following lines to your bukkit.yml:

    worlds:
      world:
        generator: BlockGrid
        
Or you can use [Multiverse](https://github.com/Multiverse/Multiverse-Core/wiki/Custom-Generator-Plugins).

Parameters
----------

The one benefit this fork has over LRFLEW’s original is that you can now specify a [block ID][]
to force your SkyWorld to populate with only one block type. You may find this useful for creative builds.

    generator: BlockGrid:<height>,<block_id>

[SkyGrid plugin]: https://github.com/LRFLEW/SkyGrid "Original SkyGrid Plugin"
[SkyGrid script]: https://github.com/SethBling/SkyGrid "Original SkyGrid Script"
[block ID]: http://www.minecraftwiki.net/wiki/Data_values#Block_IDs "List of Block IDs"
