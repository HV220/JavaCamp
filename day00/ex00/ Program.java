class Program {
    public static void main(String[] args) {

        int num = 479598;
        int sum = 0;

        String tmp = String.format("%04d", num);

        for (int i = 0; i < tmp.length(); i++) {
            char digit = tmp.charAt(i);
            sum += Integer.parseInt(String.valueOf(digit));
        }

        System.out.println(sum);
    }
}