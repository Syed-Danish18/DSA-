class ProductOfNumbers {

    ArrayList<Integer> list;

    public ProductOfNumbers() {
        list = new ArrayList<>();
    }

    public void add(int num) {
        if (num == 0) {
            list.clear();
        } else {
            list.add(num);
        }
    }

    public int getProduct(int k) {
        if (k > list.size()) {
            return 0;
        }

        int product = 1;

        for (int i = list.size() - k; i < list.size(); i++) {
            product *= list.get(i);
        }

        return product;
    }
}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */