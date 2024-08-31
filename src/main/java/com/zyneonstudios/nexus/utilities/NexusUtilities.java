package com.zyneonstudios.nexus.utilities;

import com.zyneonstudios.nexus.utilities.logger.NexusLogger;

public class NexusUtilities {

    private final static NexusLogger logger = new NexusLogger("NEXUS");

    public static NexusLogger getLogger() {
        return logger;
    }
}