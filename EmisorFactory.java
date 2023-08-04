public class EmisorFactory {

    public EmisorEnlace create(int type) {
        if (type == 2) {
            return new Hamming();
        }
        return new CRCEmisor();
    }
}