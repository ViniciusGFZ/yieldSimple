package yield.simple;

public class ThreadType {

    public static Coroutine startCoroutine(Action action) {
        Coroutine coroutine = new Coroutine(action);
        coroutine.startThread();
        return coroutine;
    }

    public static class Update implements Runnable {

        private Updatable updatable;
        private Thread thread;
        private boolean stop;
        private double fps = 60.0;

        public Update(Updatable updatable) {
            this.updatable = updatable;
            thread = new Thread(this);
            thread.setName("Yield-Update-Loop");
        }

        public void startThread() {
            thread.start();
        }

        @Override
        public void run() {
            updatable.updateStart();
            while (!stop) {
                updatable.update();
                try {
                    Thread.sleep((long) (1000.0 / fps));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public Updatable getUpdatable() {
            return updatable;
        }

        public void setUpdatable(Updatable updatable) {
            this.updatable = updatable;
        }

        public Thread getThread() {
            return thread;
        }

        public void setThread(Thread thread) {
            this.thread = thread;
        }

        public boolean isStop() {
            return stop;
        }

        public void setStop(boolean stop) {
            this.stop = stop;
        }

        public double getFps() {
            return fps;
        }

        public void setFps(double fps) {
            this.fps = fps;
        }

    }

    public static class Coroutine implements Runnable {

        private Action action;
        private Thread thread;
        private boolean interruptThredOnComplete = true;

        public Coroutine(Action action) {
            this.action = action;
            thread = new Thread(this);
            thread.setName("Yield-Coroutine");
        }

        public void startThread() {
            thread.start();
            if(interruptThredOnComplete)
            thread.interrupt();
        }

        @Override
        public void run() {
            action.onAction();
        }

        public Thread getThread() {
            return thread;
        }

        public void setThread(Thread thread) {
            this.thread = thread;
        }

        public Action getAction() {
            return action;
        }

        public void setAction(Action action) {
            this.action = action;
        }

        public boolean isInterruptThredOnComplete() {
            return interruptThredOnComplete;
        }

        public void setInterruptThredOnComplete(boolean interruptThredOnComplete) {
            this.interruptThredOnComplete = interruptThredOnComplete;
        }

    }

}
