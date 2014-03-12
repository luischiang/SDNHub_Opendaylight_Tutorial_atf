package org.opendaylight.controller.sdnhub.atf.internal;

import org.opendaylight.controller.sal.packet.Ethernet;
import org.opendaylight.controller.sal.packet.IDataPacketService;
import org.opendaylight.controller.sal.packet.IListenDataPacket;
import org.opendaylight.controller.sal.packet.IPv4;
import org.opendaylight.controller.sal.packet.Packet;
import org.opendaylight.controller.sal.packet.PacketResult;
import org.opendaylight.controller.sal.packet.RawPacket;
import org.opendaylight.controller.sal.packet.UDP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DNSspy implements IListenDataPacket {
    protected static final Logger logger = LoggerFactory
            .getLogger(DNSspy.class);
    private IDataPacketService dataPacketService = null;

    void setDataPacketService(IDataPacketService s) {
        this.dataPacketService = s;
    }

    void unsetDataPacketService(IDataPacketService s) {
        if (this.dataPacketService == s) {
            this.dataPacketService = null;
        }
    }

    @Override
    public PacketResult receiveDataPacket(RawPacket inPkt) {
        // TODO Auto-generated method stub


        try{
            
        Packet etherpack = this.dataPacketService.decodeDataPacket(inPkt);
        
            if (etherpack instanceof Ethernet) {
                logger.info("it is Ethernet");
                
                Packet ippack = etherpack.getPayload();
                
                if (ippack instanceof IPv4) {
                    
                    logger.info("it is IPv4");
                    
                    Packet udppackt = ippack.getPayload();
                    
                    if (udppackt instanceof UDP) {
                        logger.info("it is UDP");
                    }else
                    {
                        logger.info("it is not UDP");
                    }
                    
                    
                }else{
                    logger.info("not IPv4");
                }
                
                
            }else{
                logger.info("not Ethernet");
            }
        
        }catch  (Exception ex){
            logger.info("Ex");
        }
        
        return null;
    }

    void init() {
        
    }

}
