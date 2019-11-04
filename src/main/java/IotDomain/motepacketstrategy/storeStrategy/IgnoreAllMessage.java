package IotDomain.motepacketstrategy.storeStrategy;

import IotDomain.lora.LoraWanPacket;

import java.util.Optional;

public class IgnoreAllMessage implements ReceivedPacketStrategy {

    @Override
    public void addReceivedMessage(LoraWanPacket packet) { }

    @Override
    public boolean hasPackets() {
        return false;
    }

    @Override
    public Optional<LoraWanPacket> getReceivedPacket() {
        return Optional.empty();
    }
}