package com.zyneonstudios.nexus.utilities;

import com.zyneonstudios.nexus.utilities.logger.NexusLogger;
import com.zyneonstudios.nexus.utilities.strings.StringGenerator;

public class NexusUtilities {

    private final static NexusLogger logger = new NexusLogger("NEX-"+ StringGenerator.generateAlphanumericString(4));

    public static NexusLogger getLogger() {
        return logger;
    }
}